package com.example.demo.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User addUser(User user) {
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        return userRepo.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        userRepo.deleteById(id);
        return "DELETED";
    }

    @Override
    public List<User> userList() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        user.setUpdatedDate(new Date());
        return userRepo.save(user);
    }

   
    

}
