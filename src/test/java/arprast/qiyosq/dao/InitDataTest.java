/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ari-prasetiyo
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest

public class InitDataTest {

//	@Test
	@Sql(
			// executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
			scripts = "/InitData.sql")
	public void test() {
		System.out.println("InitDataTest =======================================================");
	}

//	@Sql(scripts = "/DropTables.sql")
//	@Test
	public void dropTable() {
		System.out.println("dropTable =======================================================");
	}

}
