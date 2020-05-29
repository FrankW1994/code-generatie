package io.swagger.service;

import io.swagger.dao.UserRepository;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserApiService {

    List<User> users;

    @Autowired
    private UserRepository userRepository;

    public UserApiService() {
        users = new ArrayList<>(
                Arrays.asList(
                        new User((long) 1, "Bill", "Nye", "billnye@email.com", "test", "0612345678", "20-11-1990", "20-10-2019", User.RankEnum.EMPLOYEE, User.StatusEnum.ACTIVE),
                        new User((long) 2, "Henk", "Anders", "henkanders@email.com", "test", "0687654321", "25-10-1994", "05-02-2020", User.RankEnum.CUSTOMER, User.StatusEnum.ACTIVE),
                        new User((long) 3, "Klaas", "Vaak", "klaasvaak@email.com", "test", "0600112233", "02-12-1993", "10-03-2020", User.RankEnum.CUSTOMER, User.StatusEnum.ACTIVE)
                )
        );
    }

    public List<User> getUsers()
    {
        return users;
    }

    public List<User> getUser(){return users;}

    public static User postUser(User user)
    {
        if (user.getFirstname().isEmpty() || user.getLastname().isEmpty() || user.getPassword().isEmpty() || user.getId() == null || user.getEmail().isEmpty() ||
                user.getPhone().isEmpty() || user.getBirthdate().isEmpty() || user.getRank() == null || user.getStatus() == null)
        {
            return null;
        }
        else
        {
            userRepository.save(user);
            return user;
        }
    }

}