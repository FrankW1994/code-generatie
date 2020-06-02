package io.swagger.service;

import io.swagger.dao.RepositoryAccount;
import io.swagger.dao.RepositoryUser;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserApiService {

    @Autowired
    private RepositoryUser repositoryUser;

    //public UserApiService{}

    User userError = new User(500L);

    List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User(1L, "admin", "admin", "admin@employee.com", "admin", "+31615141324", "02-92-2000", "02-02-2020", User.RankEnum.ADMIN,  User.StatusEnum.ACTIVE),
                    new User(2L, "employee", "employee", "employee@employee.com", "employee", "+31615141324", "02-92-2000", "02-02-2020", User.RankEnum.EMPLOYEE,  User.StatusEnum.ACTIVE),
                    new User(3L, "customer", "customer", "customer@customer.com", "customer", "+31615141324", "02-92-2000", "02-02-2020", User.RankEnum.CUSTOMER,  User.StatusEnum.ACTIVE),
                    new User(4L, "henk", "De Jong", "henk@De Jong.com", "henk", "+31615141324", "02-92-2000", "02-02-2020", User.RankEnum.CUSTOMER,  User.StatusEnum.ACTIVE)
            )
    );

    public List<User> getUsers(){
        return users;
    }

    public User getUserById(Long userId) {

        for (User user : users){
            if (user.getId().equals(userId)){
                return user;
            }
        }
        return userError;
    }
    public User postUser(User user){
        repositoryUser.save(user);
        return user;
    }
}
