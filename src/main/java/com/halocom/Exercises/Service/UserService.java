package com.halocom.Exercises.Service;

import com.halocom.Exercises.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser();
    void createUser(User user);
    User findByUserName(String userName);

}
