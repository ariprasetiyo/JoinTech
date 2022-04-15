/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.model.RolesModel;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public interface RolesDao extends PagingAndSortingRepository<RolesModel, Long> {

    @Query("select id from RolesModel where roleName = :nRolesName order by id desc")
    public long getIdByName(@Param("nRolesName") String roleName);
    
    @Query("from RolesModel where roleName in :nRolesName order by id desc")
    public List<RolesModel> getListByName(@Param("nRolesName") List<String> roleName);

    @Query("from RolesModel where roleName in :nRolesName order by id desc")
    public List<RolesModel> getListIdByName(@Param("nRolesName") List<String> roleName);
}

