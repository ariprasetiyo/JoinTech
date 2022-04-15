/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import arprast.qiyosq.model.DaftarPelakuModel;
import arprast.qiyosq.model.MasterItemModel;
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

//	public long saveDaftarPelaku(DaftarPelakuDto daftarPelakuDto) {
//		return em.createNativeQuery("insert into data_daftar_pelaku " +
//				"(id, nama, address, birth_day, no_ktp, no_laporan, note, pasal, status, lokasi_lapas, lokasi_tkp, tanggal_tkp, " +
//				"ref_id_komplotan, photo_location, pdf_location, is_delete, created_by, modified_by, modified_time)" +
//				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ")
//				.setParameter(1, daftarPelakuDto.getPersonalIdentity().getIdPelaku())
//				.executeUpdate();
//	}
//
//	public long saveDaftarDiri(DaftarPelakuDto daftarPelakuDto) {
//		return em.createNativeQuery("insert into data_diri " +
//				"(id, id_data_diri, no_ktp, nama, alias, address, no_hp, note, photo_profile, is_delete, created_by, modified_by, modified_time)" +
//				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ")
//				.setParameter(1,  daftarPelakuDto.getPersonalIdentity().getIdPelaku())
//				.executeUpdate();
//	}
//
//	public List<AuthorizationModel> listAllAuthorizationMenu(RequestData requestData) {
//		return em
//				.createQuery(
//						"from AuthorizationModel am "
//						+ "JOIN FETCH am.sysRoles sr "
//						+ "JOIN FETCH am.sysMenu sm "
//						+ "where sr.id = :nsysRolesId and sm.menusName is not null")
//				.setParameter("nsysRolesId", requestData.getId()).setFirstResult(requestData.getOffset())
//				.setMaxResults(requestData.getLimit()).getResultList();
//	}

    private static final String queryDaftarPelaku() {
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
                .append("where is_active = true")
                .toString();
    }

    public List<DaftarPelakuModel> getDaftarPelaku() {
        return em.createNativeQuery(queryDaftarPelaku(), "DaftarPelakuModelMapping").getResultList();

    }

    public List<MasterItemModel> getMasterItems() {
        return em.createNativeQuery(getMasterItemsQuery(), "MasterItemModelMapper").getResultList();
//		final Query query = em.createNativeQuery(getMasterItemsQuery());
//		query.unwrap(SQLQuery.class)
//				.addScalar("itemCode", StringType.INSTANCE)
//				.setResultTransformer(Transformers.aliasToBean(MasterItemModel.class));
//		return query.getResultList();
    }


}
