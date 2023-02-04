package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.UserController;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
public class DemoApplicationTests {
   
    private MockMvc mockMvc;
    
    @Mock
    UserService userService;
    
    @InjectMocks
    UserController userController;
    
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
   
	
    @Test
    public void testCreateUser () throws Exception {
        User user = constructUser();
        Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(user);
        UserDTO userDTO = constructUserDTO();
        String req = new ObjectMapper().writeValueAsString(userDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user")
                .accept(MediaType.APPLICATION_JSON).content(req)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    
    @Test
    public void testgetUser () throws Exception {
        UserDTO userDTO = constructUserDTO();
        User user = constructUser();
        String res = new ObjectMapper().writeValueAsString(userDTO);
        Mockito.when(userService.getUser(Long.valueOf("1"))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON)
                        .content(res))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
    
    @Test
    public void testgetAllUser () throws Exception {
        List<UserDTO> userDtoList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        UserDTO userDTO = constructUserDTO();
        userDtoList.add(userDTO);
        User user = constructUser();
        userList.add(user);
        String res = new ObjectMapper().writeValueAsString(userDtoList);
        Mockito.when(userService.userList()).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/user").contentType(MediaType.APPLICATION_JSON)
                        .content(res))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testDeleteUser () throws Exception {
        String res = "DELETED";
        Mockito.when(userService.deleteUser(Long.valueOf("1"))).thenReturn("DELETED");
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON)
                        .content(res))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
    
    private UserDTO constructUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("siva");
        userDTO.setLastName("MP");
        userDTO.setEmail("siva@gmail.com");
        userDTO.setPhoneNo("9999");
        userDTO.setId(Long.valueOf("1"));
        return userDTO;
    }


    private User constructUser() {
        User user = new User();
        user.setFirstName("siva");
        user.setLastName("MP");
        user.setEmail("siva@gmail.com");
        user.setPhoneNo("9999");
        user.setId(Long.valueOf("1"));
        return user;
    }
    
	
	
	
	
}
