/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import arprast.qiyosq.model.DaftarPelakuModel;
import arprast.qiyosq.model.MasterItemModel;
import arprast.qiyosq.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author ari-prasetiyo
 */

@Repository
public class DaoImpl {

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

    private static String getInsertItemTmpPOS() {
        return new StringBuilder()
                .append("insert into ( transaction_id, item_code, item_name, qty, seller_price, price_detail, basic_price, status) values ")
                .append(" (?, ?, ?, ?, ?, ?, ?, ?); ")
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
    public MasterItemModel getMasterItem(final String itemCode) {
        return (MasterItemModel) em.createNativeQuery(getMasterItemQuery(), "MasterItemModelMapper")
                .setParameter(1, itemCode).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public int insertItemTmpPOS(final String itemCode) {
        return em.createNativeQuery(getInsertItemTmpPOS())
                .setParameter(1, itemCode)
                .setParameter(2, itemCode)
                .setParameter(3, itemCode)
                .setParameter(4, itemCode)
                .setParameter(5, itemCode)
                .setParameter(6, itemCode)
                .setParameter(7, itemCode)
                .setParameter(8, itemCode)
                .setParameter(9, itemCode)
                .executeUpdate();
    }
}
