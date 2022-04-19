package arprast.qiyosq.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "master")
@SqlResultSetMapping(name = "DaftarPelakuModelMapping",
        classes = {
                @ConstructorResult(
                        targetClass = DaftarPelakuModel.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "nama", type = String.class),
                                @ColumnResult(name = "ref_id_komplotan", type = String.class),
                                @ColumnResult(name = "address", type = String.class),
                                @ColumnResult(name = "birth_day", type = Date.class),
                                @ColumnResult(name = "no_ktp", type = String.class),
                                @ColumnResult(name = "no_laporan", type = String.class),
                                @ColumnResult(name = "note", type = String.class),
                                @ColumnResult(name = "modus", type = String.class),
                                @ColumnResult(name = "pasal", type = String.class),
                                @ColumnResult(name = "status", type = String.class),
                                @ColumnResult(name = "lokasi_lapas", type = String.class),
                                @ColumnResult(name = "lokasi_tkp", type = String.class),
                                @ColumnResult(name = "tanggal_tkp", type = Date.class),
                                @ColumnResult(name = "ref_id_komplotan", type = String.class),
                                @ColumnResult(name = "photo_profile_path", type = String.class),
                                @ColumnResult(name = "pdf_file_path", type = String.class)

                        })
        })
@SqlResultSetMapping(name = "MasterItemsModelMapper",
        classes = {
                @ConstructorResult(
                        targetClass = MasterItemModel.class,
                        columns = {
                                @ColumnResult(name = "item_code", type = String.class),
                                @ColumnResult(name = "item_code_label",  type = String.class),
                                @ColumnResult(name = "item_name",  type = String.class),
                                @ColumnResult(name = "description",  type = String.class),
                                @ColumnResult(name = "sell_price",   type = Float.class),
                                @ColumnResult(name = "price_detail",  type = String.class),
                                @ColumnResult(name = "basic_price",   type = Float.class),
                                @ColumnResult(name = "unit_measure",  type = String.class),
                                @ColumnResult(name = "is_active",  type = Boolean.class),
                                @ColumnResult(name = "modified_time",  type = Date.class),
                                @ColumnResult(name = "item_type",  type = String.class),
                                @ColumnResult(name = "stock",  type = Integer.class)
                        })
        })
@SqlResultSetMapping(name = "MasterItemModelMapper",
        classes = {
                @ConstructorResult(
                        targetClass = MasterItemModel.class,
                        columns = {
                                @ColumnResult(name = "item_code", type = String.class),
                                @ColumnResult(name = "item_code_label",  type = String.class),
                                @ColumnResult(name = "item_name",  type = String.class),
                                @ColumnResult(name = "description",  type = String.class),
                                @ColumnResult(name = "sell_price",   type = Float.class),
                                @ColumnResult(name = "price_detail",  type = String.class),
                                @ColumnResult(name = "basic_price",   type = Float.class),
                                @ColumnResult(name = "unit_measure",  type = String.class),
                                @ColumnResult(name = "item_type",  type = String.class),
                                @ColumnResult(name = "stock",  type = Integer.class)
                        })
        })

public class MapperModel {

    @Id
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
