package io.swagger.service;

import io.swagger.model.User;

import java.util.List;

public class UserApiService {

    List<User> users;

    public UserApiService() {
        User customer = new User();
        customer.setId((long) 1);
        customer.setFirstname("Frank");
        customer.setLastname("Wester");
        customer.setPassword("test");
        customer.setEmail("frank@test.nl");
        customer.setRegistrationdate("05-05-2020");
        customer.setBirthdate("25-10-1994");
        customer.setPhone("0612345678");
        customer.setRank(User.RankEnum.CUSTOMER);
        customer.setStatus(User.StatusEnum.ACTIVE);
        users.add(customer);
        User employee = new User();
        employee.setId((long) 2);
        employee.setFirstname("Billy");
        employee.setLastname("Bob");
        employee.setPassword("test");
        employee.setEmail("billy@test.nl");
        employee.setRegistrationdate("05-05-2020");
        employee.setBirthdate("25-10-1995");
        employee.setPhone("0687654321");
        employee.setRank(User.RankEnum.EMPLOYEE);
        employee.setStatus(User.StatusEnum.ACTIVE);
        users.add(employee);
    }

    public List<User> getUsers()
    {
        return users;
    }

    public postUser(Object userObject)
    {
        User user = new User();
        user.equals(userObject);
        users.add(user);
        return Https
    }


}