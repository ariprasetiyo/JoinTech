/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.model.RolesModel;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public interface UserGroupDao extends JpaRepository<RolesModel, Long> {

	@Query("select count(id) from RolesModel where id = :nId ")
	public int countUserGroupById(@Param("nId") long id);

	@Query("select count(id) from RolesModel where roleName = :nRoleName ")
	public int countUserGroupByRoleName(@Param("nRoleName") String roleName);

}
