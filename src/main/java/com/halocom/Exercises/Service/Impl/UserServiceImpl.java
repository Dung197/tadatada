package com.halocom.Exercises.Service.Impl;

import com.halocom.Exercises.Model.User;
import com.halocom.Exercises.Repository.UserRepository;
import com.halocom.Exercises.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public void createUser(User user) {
        user.setCreateDate(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
