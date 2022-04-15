
package arprast.qiyosq.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.model.MenusModel;

@Repository
public interface MenusDao extends PagingAndSortingRepository<MenusModel, Long> {

	@Query("select count(1) from MenusModel WHERE menusName =:nMenuName")
	public int countMenuByMenuName(@Param("nMenuName") String menuName);

	@Query("select count(id) from MenusModel WHERE id = :menuId")
	public int countMenuById(@Param("menuId") Long idMenu);
	
	@Modifying
	@Query("delete from MenusModel where id = :menuId")
	public int deleteMenu(@Param("menuId") long idMenu);

}
