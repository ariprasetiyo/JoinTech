/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.dto.ScreenMenuDto;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.MenusModel;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public interface AuthorizationDao extends PagingAndSortingRepository<AuthorizationModel, Long> {
	
//	@Query("from MenusModel where menusName like %:searchUserName% order by menusName asc")
//	public List<MenusModel> listOfMenus(String keySearch, Pageable pageable);

//	@Query("select id from AuthorizationModel where sysRoles.roleName = :nRoleName order by id desc ")
//	public  long getIdByNameAuthorization(@Param("nRoleName") String nameAuthority);

	// @Query("select a.MenuModel.url as patternDispatcherUrl, b.roleName as
	// roleName from AuthorizationModel a left join a.sysRoles b ")
	// public List<AuthorizationDto> listAll2();

	@Query("SELECT new arprast.qiyosq.dto.AuthorizationDto(a.sysMenu.url as patternDispatcherUrl, b.roleName as roleName) from AuthorizationModel a left join a.sysRoles b where a.sysMenu.url is not null order by a.sysMenu.url ")
	public  List<AuthorizationDto> listRolenameAndDispatcherUrl();

	@Modifying(clearAutomatically = true)
	// @Transactional
	@Query("update AuthorizationModel a set a.parent.id = :nparentId where a.sysRoles.id = :nId")
	public  void updateSysRoleId(@Param("nId") long id, @Param("nparentId") Long parentId);

	/* Query untuk ambil data SysAuthorization 
	 * reference other AuthorizationDaoImpl method listAllAuthorizationMenu
	 */
	@Deprecated
	@Query("from AuthorizationModel  where sysRoles.id = :nsysRolesId and sysMenu.menusName is not null")
	public  List<AuthorizationModel> getForScreenMenu(@Param("nsysRolesId") long idSysRole);

	/*
	 * Query count parentId data SysAuthorization. NamedNativeQuery is
	 * "SysAuthorization.countParentId" Could using like this method from
	 * controller without pass interface Query q =
	 * em.createNamedQuery("SysAuthorization.countParentId");
	 * log.debug(q.toString()); q.setParameter("nparentId", 72);
	 */
	@Query(nativeQuery = true)
	public  Integer countParentId(@Param("nparentId") long nparentId);

	/*
	 * Query for rekursif view menu base on parent id
	 */
	@Query("select parent.id  from AuthorizationModel where id = :nId ")
	public  Long getParentId(@Param("nId") long ntId);

	// Using @NamedNativeSQL and sqlResultsetMapping
	// resultSetMapping = "SysAuthorization.listScreenMenu" in Model
	// parentId by default setting must be set 0
	@Query(nativeQuery = true)
	public  List<ScreenMenuDto> listScreenMenu(@Param("nsysRolesId") List<Long> id,
			@Param("nparentId") Long parentId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update AuthorizationModel a set a.isInsert = :nisInsert, a.isUpdate = :nisUpdate, a.isDelete = :nisDelete, a.disabled = :ndisabled where a.id = :nId")
	public  int updateAuthorization(@Param("nId") long id, @Param("nisInsert") boolean isInsert,
			@Param("nisUpdate") boolean isUpdate, @Param("nisDelete") boolean isDelete,
			@Param("ndisabled") boolean disabled);

	@Query(value = "select new  arprast.qiyosq.dto.AuthorizationDto( SA.createdTime as createTime, SA.modifiedTime as modifyTime, SA.id as id, SA.sysMenu.menusName as menuName,  SA.parent.id as parentId,  SA.isUpdate as isUpdate,  SA.isDelete as isDelete,  SA.isInsert as isInsert,  SA.isRead as isRead ) from AuthorizationModel SA  where SA.id =:nidMenu ")
	public  AuthorizationDto getDataAuthorizationById(@Param("nidMenu") Long idMenu);

	@Query(value = "select count(id) from AuthorizationModel where  sysRoles.id = :idRole ")
	public long countByRole(@Param("idRole") Long idRole);

	@Modifying
	@Query(value = "delete from AuthorizationModel where id =:idRole")
    void delete(@Param("idRole") Long id);
}
