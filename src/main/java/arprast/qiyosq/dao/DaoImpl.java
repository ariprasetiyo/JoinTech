/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import arprast.qiyosq.model.*;
import arprast.qiyosq.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author ari-prasetiyo
 */

@Repository
public class DaoImpl {

    private static final Logger logger = LoggerFactory.getLogger(DaoImpl.class);
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final TypeReference<PriceDetail> priceDetailRef = new TypeReference<PriceDetail>() {
    };
    private static final TypeReference<POSHeaderTmpModel> posHeaderTmpModelRef = new TypeReference<POSHeaderTmpModel>() {
    };
    private static final TypeReference<POSDetailTmpModel> posDetailTmpModellRef = new TypeReference<POSDetailTmpModel>() {
    };
    private static final ObjectWriter writePriceDetailJson = jsonMapper.writerFor(priceDetailRef);
    private static final ObjectWriter posHeaderTmpModeWriterJson = jsonMapper.writerFor(posHeaderTmpModelRef);
    private static final ObjectWriter posDetailTmpModellWriteJson = jsonMapper.writerFor(posDetailTmpModellRef);

    @Autowired
    @PersistenceContext
    EntityManager em;

    private static String queryDaftarPelaku() {
        return new StringBuilder()
                .append("select ddi.id , ddi.nama , ref_id_komplotan , ddi.address , ddi.birth_day , no_ktp,  no_laporan , ddp.note ,\n")
                .append("ddp.modus , ddp.pasal , ddi.status , ddp.lokasi_lapas , ddp.lokasi_tkp , ddp.tanggal_tkp ,\n")
                .append("ddi.photo_location as photo_profile_path, ddp.pdf_location as pdf_file_path\n")
                .append("from data_daftar_pelaku ddp inner join data_personal_indentity ddi on ddp.id = ddi.id  ")
                .toString();
    }

    private static String getMasterItemsQuery() {
        return new StringBuilder()
                .append("select imi.item_code, item_code_label, item_name, description, sell_price, price_detail, basic_price, unit_measure, is_active, modified_time, item_type, imis.stock ")
                .append("from inventory_master_item imi left join inventory_master_item_stock imis on imi.item_code  = imis.item_code  ")
                .append("where item_code_label like ? or item_name like ?  and is_active = true limit ?,? ")
                .toString();
    }

    private static String getMasterItemQuery() {
        return new StringBuilder()
                .append("select imi.item_code, item_code_label, item_name, description, sell_price, price_detail, basic_price, unit_measure, item_type, imis.stock ")
                .append("from inventory_master_item imi left join inventory_master_item_stock imis on imi.item_code  = imis.item_code  ")
                .append("where imi.item_code = ? and is_active = true ")
                .toString();
    }

    private static String getPOSHeaderTmpQuery() {
        return new StringBuilder()
                .append("select request_id, customer_name , customer_id , phone_number , address , payment_method, total_trx_amount,  total_discount_amount , total_paid_amount, username, created_time ")
                .append("from pos_header_tmp where username = ? and request_id = ? ")
                .toString();
    }

    private static String getInsertHeaderPOS() {
        return new StringBuilder()
                .append("INSERT INTO pos_header_tmp ")
                .append("(request_id, customer_name, customer_id, phone_number, address, payment_method, total_trx_amount, total_discount_amount, total_paid_amount, username, created_time) ")
                .append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp())")
                .toString();
    }

    private static String buildLike(final String search) {
        return new StringBuilder().append(Util.PERCENTAGE).append(search).append(Util.PERCENTAGE).toString();
    }

    public List<DaftarPelakuModel> getDaftarPelaku() {
        return em.createNativeQuery(queryDaftarPelaku(), "DaftarPelakuModelMapping").getResultList();
    }

    //		final Query query = em.createNativeQuery(getMasterItemsQuery());
    //		query.unwrap(SQLQuery.class)
    //				.addScalar("itemCode", StringType.INSTANCE)
    //				.setResultTransformer(Transformers.aliasToBean(MasterItemModel.class));
    //		return query.getResultList();
    @SuppressWarnings("unchecked")
    public List<MasterItemModel> getMasterItems(final String search, final int offset, final int limit) {
        return em.createNativeQuery(getMasterItemsQuery(), "MasterItemsModelMapper")
                .setParameter(1, buildLike(search))
                .setParameter(2, buildLike(search))
                .setParameter(3, offset)
                .setParameter(4, limit)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public POSHeaderTmpModel getPOSHeaderTmp(final String userName, final String requestId) {
        try {
            return (POSHeaderTmpModel) em.createNativeQuery(getPOSHeaderTmpQuery(), "POSHeaderTmpMapper")
                    .setParameter(1, userName)
                    .setParameter(2, requestId)
                    .getSingleResult();
        } catch (NoResultException nre) {
            logger.info("no result data for {}, user name {}. query={}", requestId, userName, getPOSHeaderTmpQuery());
        } catch (Exception e) {
            logger.error("error getPOSHeaderTmp={} {}",  requestId, userName, e);
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public int insertPOSHeaderTmp(POSHeaderTmpModel posHeaderTmpModel) {
        try {
            return em.createNativeQuery(getInsertHeaderPOS())
                    .setParameter(1, posHeaderTmpModel.getRequestId())
                    .setParameter(2, posHeaderTmpModel.getCustomerName())
                    .setParameter(3, posHeaderTmpModel.getCustomerId())
                    .setParameter(4, posHeaderTmpModel.getPhoneNumber())
                    .setParameter(5, posHeaderTmpModel.getAddress())
                    .setParameter(6, posHeaderTmpModel.getPaymentMethod().id)
                    .setParameter(7, posHeaderTmpModel.getTotalTrxAmount())
                    .setParameter(8, posHeaderTmpModel.getTotalDiscountAmount())
                    .setParameter(9, posHeaderTmpModel.getTotalPaidAmount())
                    .setParameter(10, posHeaderTmpModel.getUsername())
                    .executeUpdate();
        } catch (Exception e){
            try {
                logger.error("error insertPOSHeaderTmp={}, query={}",  posHeaderTmpModeWriterJson.writeValueAsString(posHeaderTmpModel), getInsertHeaderPOS(), e);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    public MasterItemModel getMasterItem(final String itemCode) {
        return (MasterItemModel) em.createNativeQuery(getMasterItemQuery(), "MasterItemModelMapper")
                .setParameter(1, itemCode).getSingleResult();
    }

    private static String getPOSDetalTmp() {
        return new StringBuilder()
                .append("insert into pos_detail_tmp ( request_id, item_code, item_code_label, item_name, description, qty, sell_price, price_detail, basic_price, item_type) values ")
                .append(" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ")
                .toString();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public int insertItemTmpPOS(final POSDetailTmpModel model) {
        String priceDetailJson = null;
        try {
            priceDetailJson = writePriceDetailJson.writeValueAsString(model.getPriceDetail());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            return em.createNativeQuery(getPOSDetalTmp())
                    .setParameter(1, model.getRequestId())
                    .setParameter(2, model.getItemCode())
                    .setParameter(3, model.getItemCodeLabel())
                    .setParameter(4, model.getItemName())
                    .setParameter(5, model.getDescription())
                    .setParameter(6, model.getQty())
                    .setParameter(7, model.getSellPrice())
                    .setParameter(8, priceDetailJson)
                    .setParameter(9, model.getBasicPrice())
                    .setParameter(10, model.getItemType().id)
                    .executeUpdate();
        }catch (Exception e){
            try {
                logger.error("error insertItemTmpPOS={}, query={}",  posDetailTmpModellWriteJson.writeValueAsString(model), getPOSDetalTmp(), e);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }
}
