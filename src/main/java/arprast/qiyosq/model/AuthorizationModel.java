/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import arprast.qiyosq.dto.ScreenMenuDto;

/**
 *
 * @author ari-prasetiyo
 */
@Entity
@Table(name = "sys_authorization")
@NamedNativeQueries({
    //tidak menggunakan resultClass karena hanya count saja
    @NamedNativeQuery(name = "AuthorizationModel.countParentId", query = "select (sum(ChildCount)+1) as level from "
            + "( select (select count(*) from sys_authorization where parent_id= m.id ) as ChildCount "
            + "from sys_authorization m where m.parent_id =:nparentId group by m.id ) a "),
    @NamedNativeQuery(name = "AuthorizationModel.listScreenMenu",
            query = "select  a.id as id , sm.menus_name as name_menu, sm.url as pattern_dispatcher_url, coalesce(a.parent_id, 0) as parent_id, coalesce(b.con, 0) as con  from "
            + "sys_authorization as a "
            + "LEFT join ( select parent_id, count(1) as con from sys_authorization GROUP by parent_id ) as b "
            + " on a.id = b.parent_id  left join sys_menu sm on a.sys_menu_id = sm.id where sys_roles_id in :nsysRolesId and coalesce(a.parent_id, 0) = :nparentId",
            //resultClass = SysScreenMenuDto.class,
            resultSetMapping = "AuthorizationModel.listScreenMenu"
    )
})
@SqlResultSetMapping(name = "AuthorizationModel.listScreenMenu",
        classes = {
            @ConstructorResult(
                    targetClass = ScreenMenuDto.class,
                    columns = {
                        @ColumnResult(name = "id", type = long.class),
                        @ColumnResult(name = "name_menu", type = String.class),
                        @ColumnResult(name = "pattern_dispatcher_url", type = String.class),
                        @ColumnResult(name = "parent_id", type = Long.class),
                        @ColumnResult(name = "con", type = Integer.class)

                    })
        })

//@SqlResultSetMapping(name = "SysAuthorization.listScreenMenu",
//        entities = @EntityResult(entityClass = SysAuthorization.class,
//                fields = {
//                    @FieldResult(name = "id", column = "id"),
//                    @FieldResult(name = "nameMenu", column = "name_menu"),
//                    @FieldResult(name = "patternDispatcherUrl", column = "pattern_dispatcher_url"),
//                    @FieldResult(name = "parent.id", column = "parent_id"),
//                    @FieldResult(name = "counts", column = "con")
//                }))
public class AuthorizationModel extends ModelEntity {
	
	private static final long serialVersionUID = 2432434267482377275L;

//    @Column(name = "pattern_dispatcher_url", length = 100, nullable = true)
//    private String patternDispatcherUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "sys_roles_id")
    private RolesModel sysRoles;

//    @Column(name = "name_menu")
//    public String nameMenu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "sys_menu_id")
    private MenusModel sysMenu;

    @Column(name = "is_update")
    private boolean isUpdate;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "is_insert")
    private boolean isInsert;

    @Column(name = "is_read")
    private boolean isRead;

    @Transient
    private Integer counts;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    public AuthorizationModel parent;

    public boolean isIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isIsInsert() {
        return isInsert;
    }

    public void setIsInsert(boolean isInsert) {
        this.isInsert = isInsert;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public AuthorizationModel getParent() {
        return parent;
    }

    public void setParent(AuthorizationModel parent) {
        this.parent = parent;
    }

    public void setParent(long parent) {
        AuthorizationModel sysAuthorization = new AuthorizationModel();
        sysAuthorization.setId(parent);
        this.parent = sysAuthorization;
    }

    public RolesModel getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(RolesModel sysRoles) {
        this.sysRoles = sysRoles;
    }

    public void setSysRoles(long id) {
        RolesModel byId = new RolesModel();
        byId.setId(id);
        this.sysRoles = byId;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public MenusModel getSysMenu() {
        return sysMenu;
    }

    public void setSysMenu(MenusModel sysMenu) {
        this.sysMenu = sysMenu;
    }

    public void setSysMenu(long id) {
        MenusModel sysMenus = new MenusModel();
        sysMenus.setId(id);
        this.sysMenu = sysMenus;
    }

}
