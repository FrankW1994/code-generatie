package io.swagger.service;

import io.swagger.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserApiService {

    List<User> users;

    public UserApiService() {

    }

    public List<User> getUsers()
    {
        return users;
    }

//    public postUser(Object userObject)
//    {
//        User user = new User();
//        user.equals(userObject);
//        users.add(user);
//        return Https
//    }


}