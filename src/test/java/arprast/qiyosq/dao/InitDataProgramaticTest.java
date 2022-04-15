/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.MenusModel;
import arprast.qiyosq.model.RolesModel;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.model.UserRolesModel;

/**
 *
 * @author ari-prasetiyo
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class InitDataProgramaticTest {

    @Autowired
    RolesDao sysRolesDao;

    @Autowired
    UserDao sysUserDao;

    @Autowired
    AuthorizationDao authorizationDao;

    @Autowired
    UserRolesDao sysUserRolesDao;

    @Autowired
    MenusDao sysMenusDao;

    //@Test
    public void testAll() {
        List<AuthorizationModel> listMenu = authorizationDao.getForScreenMenu(1);
//        SysAuthorization data = new SysAuthorization();
//        data.setNameMenu("a");
//        listMenu.add(data);
//        
//        data = new SysAuthorization();
//        data.setNameMenu("b");
//        listMenu.add(data);
        if (listMenu.size() > 0) {
            System.out.println("----------------------------" + listMenu.size());
        }

        for (AuthorizationModel menu : listMenu) {
            System.out.println("----------------------------" + menu.getSysMenu().getMenusName());
        }
    }

//    @Test
    public void initDataRoles() {
        RolesModel sysRoles = new RolesModel();
        sysRoles.setRoleName("admin");
        sysRoles = sysRolesDao.save(sysRoles);
        Assert.assertNotNull(sysRoles.getId());

        sysRoles = new RolesModel();
        sysRoles.setRoleName("approval");
        sysRoles = sysRolesDao.save(sysRoles);
        Assert.assertNotNull(sysRoles.getId());

        sysRoles = new RolesModel();
        sysRoles.setRoleName("user");
        sysRoles = sysRolesDao.save(sysRoles);
        Assert.assertNotNull(sysRoles.getId());

        sysRoles = new RolesModel();
        sysRoles.setRoleName("public");
        sysRoles = sysRolesDao.save(sysRoles);
        Assert.assertNotNull(sysRoles.getId());
    }

    //@Test
//    @Ignore
    public void initDataSysAuthorization() {

        Long idRoles = sysRolesDao.getIdByName("admin");
        Assert.assertNotNull(idRoles);
        AuthorizationModel sysAuthorization = new AuthorizationModel();

        //sysAuthorization.setParent(null);
        //sysAuthorization.setSysRoles(idRoles);
        authorizationDao.updateSysRoleId(idRoles, null);
        authorizationDao.deleteAll();
        
        //Parent
        sysAuthorization.setSysMenu(1);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setParent(null);
        sysAuthorization.setSysRoles(idRoles);
        AuthorizationModel parentDashBoard = authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent child
        sysAuthorization = new AuthorizationModel();
        sysAuthorization.setSysMenu(2);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setSysRoles(idRoles);
        sysAuthorization.setParent(parentDashBoard);
        authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent child
        sysAuthorization = new AuthorizationModel();
        sysAuthorization.setSysMenu(3);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setSysRoles(idRoles);
        sysAuthorization.setParent(parentDashBoard);
        authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent
        sysAuthorization = new AuthorizationModel();
        sysAuthorization.setSysMenu(4);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setParent(null);
        sysAuthorization.setSysRoles(idRoles);
        AuthorizationModel parentUtility = authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent child
        sysAuthorization = new AuthorizationModel();
        sysAuthorization.setSysMenu(5);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setParent(parentUtility);
        sysAuthorization.setSysRoles(idRoles);
        AuthorizationModel parentSettingUser = authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent Parent child
        sysAuthorization = new AuthorizationModel();
        sysAuthorization.setSysMenu(6);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setParent(parentSettingUser);
        sysAuthorization.setSysRoles(idRoles);
        authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent Parent child
        sysAuthorization = new AuthorizationModel();
        sysAuthorization.setSysMenu(7);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setParent(parentSettingUser);
        sysAuthorization.setSysRoles(idRoles);
        authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent child
        sysAuthorization = new AuthorizationModel();
        sysAuthorization.setSysMenu(8);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setParent(parentUtility);
        sysAuthorization.setSysRoles(idRoles);
        authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent child
        sysAuthorization = new AuthorizationModel();
       sysAuthorization.setSysMenu(9);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setParent(parentUtility);
        sysAuthorization.setSysRoles(idRoles);
        authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());

        //Parent child
        sysAuthorization = new AuthorizationModel();
        sysAuthorization.setSysMenu(10);
        sysAuthorization.setIsDelete(true);
        sysAuthorization.setIsInsert(true);
        sysAuthorization.setIsRead(true);
        sysAuthorization.setIsUpdate(true);
        sysAuthorization.setParent(parentUtility);
        sysAuthorization.setSysRoles(idRoles);
        authorizationDao.save(sysAuthorization);
        Assert.assertNotNull(sysAuthorization.getId());
    }

    //@Test
    public void initDataSysUser() {
        sysUserRolesDao.deleteAll();
        sysUserDao.deleteAll();

        Long idSysRoles = sysRolesDao.getIdByName("admin");
        Assert.assertNotNull(idSysRoles);

        UserModel sysUser = new UserModel();
        sysUser.setUsername("ari");
        sysUser.setPassword("1234");
        sysUser.setEmail("prasetiyooo@gmail.com");
        sysUser.setIsActive(true);
        sysUser.setNoHp("085645480401");
        sysUser = sysUserDao.save(sysUser);
        Assert.assertNotNull(sysUser.getId());

        UserRolesModel sysUserRoles = new UserRolesModel();
        //sysUserRoles.setSysUser(sysUser);
//        sysUserRoles.setSysRoles(idSysRoles);
        sysUserRolesDao.save(sysUserRoles);
        Assert.assertNotNull(sysUserRoles.getId());

        sysUser = new UserModel();
        sysUser.setUsername("ari prasetiyo");
        sysUser.setPassword("12345");
        sysUser.setEmail("prasetiyooo2@gmail.com");
        sysUser.setIsActive(true);
        sysUser.setNoHp("085645480401");
        sysUser = sysUserDao.save(sysUser);
        Assert.assertNotNull(sysUser.getId());

        idSysRoles = sysRolesDao.getIdByName("approval");
        sysUserRoles = new UserRolesModel();
        //sysUserRoles.setSysUser(sysUser);
//        sysUserRoles.setSysRoles(idSysRoles);
        sysUserRolesDao.save(sysUserRoles);
        Assert.assertNotNull(sysUserRoles.getId());

        idSysRoles = sysRolesDao.getIdByName("public");
        sysUserRoles = new UserRolesModel();
        //sysUserRoles.setSysUser(sysUser);
//        sysUserRoles.setSysRoles(idSysRoles);
        sysUserRolesDao.save(sysUserRoles);
        Assert.assertNotNull(sysUserRoles.getId());

        sysUser = new UserModel();
        sysUser.setUsername("public");
        sysUser.setPassword("public");
        sysUser.setEmail("public@gmail.com");
        sysUser.setIsActive(true);
        sysUser.setNoHp("085645480401");
        sysUser = sysUserDao.save(sysUser);
        Assert.assertNotNull(sysUser.getId());

        idSysRoles = sysRolesDao.getIdByName("public");
        sysUserRoles = new UserRolesModel();
        //sysUserRoles.setSysUser(sysUser);
//        sysUserRoles.setSysRoles(idSysRoles);
        sysUserRolesDao.save(sysUserRoles);
        Assert.assertNotNull(sysUserRoles.getId());

    }

    @PersistenceContext
    public EntityManager em;

    @Ignore
    @Transactional
    public void readRolesAndDipatcherUrl() {
//        SysAuthorizationImpl call = new SysAuthorizationImpl();
//        call.test();

        Iterable<AuthorizationDto> sysAuthorizations = authorizationDao.listRolenameAndDispatcherUrl();
        for (AuthorizationDto sysAuthorization : sysAuthorizations) {
            System.out.print(sysAuthorization.getRoleName());
            System.out.println(":" + sysAuthorization.getUrl());
        }

//        List<SysAuthorizationDto> sysAuthorizations = (List<SysAuthorizationDto>) em.createQuery("select a.patternDispatcherUrl as patternDispatcherUrl, b.roleName as roleName from SysAuthorization a left join a.sysRoles b ").getResultList();
//        for (SysAuthorizationDto sysAuthorization : sysAuthorizations) {
//            System.out.print(sysAuthorization.getRoleName());
//            System.out.println(":" + sysAuthorization.getPatternDispatcherUrl());
//        }
    }

    @Ignore
    public void insertDataMenu() {
        MenusModel sysMenus = new MenusModel();
        sysMenus.setMenusName("Dashboard");
        sysMenus.setUrl(null);
        Long id = sysMenusDao.save(sysMenus).getId();
        Assert.assertNotNull(id);

        sysMenus = new MenusModel();
        sysMenus.setMenusName("Dashboard V1");
        sysMenus.setUrl("/Dashboard/V1");
        id = sysMenusDao.save(sysMenus).getId();
        Assert.assertNotNull(id);
        
        sysMenus = new MenusModel();
        sysMenus.setMenusName("Dashboard V2");
        sysMenus.setUrl("/Dashboard/V2");
        id = sysMenusDao.save(sysMenus).getId();
        Assert.assertNotNull(id);

    }
}
