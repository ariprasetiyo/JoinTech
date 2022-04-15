/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import arprast.qiyosq.dto.UserDto;

/**
 *
 * @author ari-prasetiyo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApiTest {

    Logger log = LoggerFactory.getLogger(UserApiTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testApiUserList() throws Exception {
    	
        MvcResult mvcResult = mockMvc.perform(post("/admin/v1/api/user/list")
        		.contentType(MediaType.APPLICATION_JSON)
                .param("offset", "10")
                .param("limit", "10"))
                .andExpect(request().asyncStarted())
                .andExpect(status().isOk())
                .andReturn();
        
        MvcResult mvcResultCompletableFuture = this.mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print())
				.andExpect(status().isOk())
        		.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn();
        
		String content = mvcResultCompletableFuture.getResponse().getContentAsString();
		log.debug("hallo {}", content);
       
    }
    
    /*
     * http://localhost:8181/admin/v1/api/user/saveUser
     */
    @Test
    public void testSaveUser() throws Exception {
    	
    	 MvcResult mvcResult = mockMvc.perform(post("/admin/v1/api/user/saveUser")
    			 .contentType(MediaType.APPLICATION_JSON)
                 .param("username", userDto().getUsername())
                 .param("name", userDto().getName())
                 .param("password",  userDto().getPassword())
                 .param("email",  userDto().getEmail())
    			 .param("noHp",  userDto().getNoHp())
    			 .param("isActive", String.valueOf(userDto().isIsActive()))
    			 .param("selectRole[]",  "1,2")		 
    			 )
    			 .andExpect(request().asyncStarted())
                 //.andExpect(status().isOk())
                 .andReturn();
    	 
    	 MvcResult mvcResultAsynchronous = this.mockMvc.perform(asyncDispatch(mvcResult))
    			 .andDo(print())
    			 //.andExpect(status().isOk())
         		 .andExpect(content().contentType("application/json;charset=UTF-8"))
 				 .andReturn();
    	 
    	 String content = mvcResultAsynchronous.getResponse().getContentAsString(); 
    	 log.debug("hallo {}",content);
    }
    
    private static UserDto userDto(){
    	UserDto userDto = new UserDto();
    	userDto.setName("Ari Prasetiyo");
    	userDto.setUsername("s");
    	userDto.setIsActive(true);
    	userDto.setEmail("prasetiyo@gmail.comc");
    	userDto.setNoHp("1234567890123");
    	userDto.setPassword("12344");
    	long[] rolesId = {1,2,3};
    	//userDto.setRoleId(rolesId);
    	return userDto;
    }
}
