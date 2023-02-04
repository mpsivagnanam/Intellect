package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;



public interface UserService {

   public User addUser(User user);
   public User updateUser(User user);
   public String deleteUser(Long id);
   public User getUser(Long id);
   public List<User> userList();

   
}
