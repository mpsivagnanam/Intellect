package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
/**
 * User api used to handle user functions like create, update, delete, get function
 * @author siva
 */
@Tag(name = "User api", description = "To handle all user CRUD operation")
@RestController
@RequestMapping("user")
@CrossOrigin(origins="*")
public class UserController {
    
    @Autowired
    UserService userService;
    /**
     * This method return the user list 
     * @return
     */
    @Operation(summary = "Get all user list", description = "Get all user list by passing user api request", tags = { "user" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized API", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUserList(){
        List<User> userList = userService.userList();
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        userList.forEach(e->{
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(e, userDTO);
            userDTOList.add(userDTO);
        });
        return new ResponseEntity<List<UserDTO>>(userDTOList, HttpStatus.OK);
        
    }
    
    /**
     * This method used to get the particular user by passing user id
     * @param id
     * @return
     */
    @Operation(summary = "Get user detail", description = "Get particular user detail by passing user id", tags = { "user" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized API", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
       UserDTO userDTO = new UserDTO();
       User user =  userService.getUser(id);
       BeanUtils.copyProperties(user, userDTO);
       return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
        
    }
    
    /**
     * This method used to create a user and saved in DB
     * @param user
     * @return
     */
    @Operation(summary = "Create User", description = "Create user by passing user fields", tags = { "user" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized API", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = userService.addUser(user);
        BeanUtils.copyProperties(user, userDTO);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
        
    }
    
    /**
     * This method used to used to update the user details
     * @param user
     * @return
     */
    @Operation(summary = "Update user", description = "Update user by passing the updated user fields", tags = { "user" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized API", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = userService.updateUser(user);
        BeanUtils.copyProperties(user, userDTO);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
        
    }
    
    /**
     * This method used to delete the user by passing user id
     * @param id
     * @return
     */
    @Operation(summary = "Delete user", description = "Delete user by passing user id", tags = { "user" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized API", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        String status=  userService.deleteUser(id);
        return new ResponseEntity<String>(status, HttpStatus.OK);
     
        
    }

}
