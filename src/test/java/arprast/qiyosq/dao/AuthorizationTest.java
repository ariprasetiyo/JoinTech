/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import arprast.qiyosq.dao.AuthorizationDao;
import arprast.qiyosq.model.AuthorizationModel;

/**
 *
 * @author ari-prasetiyo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorizationTest {

    Logger log = LoggerFactory.getLogger(AuthorizationTest.class);
    @Autowired
    AuthorizationDao dsSysAuthorization;

    //@Test
    public void getAuthorizationById() {
//        AuthorizationModel result = dsSysAuthorization.findOne(Long.valueOf(132));
        // log.debug("test--" + result.getSysMenu().getMenusName());
//        Assert.assertNotNull(result.getSysMenu().getMenusName());
    }
}
