/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import java.util.List;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import arprast.qiyosq.dto.AuthorizationDto;

import org.apache.commons.collections.map.MultiValueMap;

/**
 *
 * @author ari-prasetiyo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadDataTest {
    
    Logger log = Logger.getLogger(ReadDataTest.class);
    @Autowired
    AuthorizationDao authorizationDao;
    
    @Test
    public void readDataForAuthorization() {
        MultiValueMap map = new MultiValueMap();
        
        List<AuthorizationDto> results = authorizationDao.listRolenameAndDispatcherUrl();
        for (AuthorizationDto result : results) {
            log.debug(result.getRoleName() + " : " + result.getUrl());
        }
        
        for (AuthorizationDto sysAuthorization : results) {
            map.put(sysAuthorization.getUrl(), sysAuthorization.getRoleName());
        }
        
        for (Object a : map.keySet()) {
            String[] coa = map.get(a).toString().replaceAll("\\[|\\]|\\s", "").split(",");
            log.debug("--- : " + a);
            for (String s : coa) {
                log.debug("------> :|" + s+"|");
            }
        }
    }
}
