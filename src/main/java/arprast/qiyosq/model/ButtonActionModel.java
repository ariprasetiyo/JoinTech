package arprast.qiyosq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="sys_button_action")
public class ButtonActionModel extends ModelNoIdEntity {

    @Id
    @Column(name ="button_name", length = 100)
    public String button_name;

    @Column(length = 100)
    public String alias;

    @Column(length = 512)
    public String description;
}
