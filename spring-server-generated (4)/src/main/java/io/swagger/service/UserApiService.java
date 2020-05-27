package io.swagger.service;

import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserApiService {

    List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User((long) 1, "Bill", "Nye", "billnye@email.com", "test", "0612345678", "20-11-1990", "20-10-2019", User.RankEnum.EMPLOYEE, User.StatusEnum.ACTIVE),
                    new User((long) 2, "Henk", "Anders", "henkanders@email.com", "test","0687654321", "25-10-1994", "05-02-2020", User.RankEnum.CUSTOMER, User.StatusEnum.ACTIVE),
                    new User((long) 3, "Klaas", "Vaak", "klaasvaak@email.com", "test", "0600112233", "02-12-1993", "10-03-2020", User.RankEnum.CUSTOMER, User.StatusEnum.ACTIVE)
            )
    );

    public List<User> getUsers()
    {
        return users;
    }

    public List<User> getUser(String query)
    {
        ArrayList<User> result = new ArrayList<>();

        for (User user : users)
        {
            if (user.toString().equals(query))
            {
                result.add(user);
            }
        }

        return result;
    }

//    public postUser(Object userObject)
//    {
//        User user = new User();
//        user.equals(userObject);
//        users.add(user);
//        return Https
//    }


}