package io.swagger.dao;

import io.cucumber.java.Before;
import io.swagger.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private RepositoryUser repositoryUser;

    @Before()
    public void setUp() throws Exception {
    }

    @Test
    public void getUsersFromFirstNameShouldReturnNotNull() throws Exception {
        List<User> users = repositoryUser.findByFirstname("Henk");
        assertNotNull(users);
    }

    @Test
    public void getUsersFromLastNameShouldReturnNotNull() throws Exception {
        List<User> users = repositoryUser.findByLastname("Anders");
        assertNotNull(users);
    }

    @Test
    public void getUsersFromRankShouldReturnNotNull() throws Exception {
        List<User> users = repositoryUser.findByRank(User.RankEnum.EMPLOYEE);
        assertNotNull(users);
    }

    @Test
    public void getUsersFromStatusShouldReturnNotNull() throws Exception {
        List<User> users = repositoryUser.findByStatus(User.StatusEnum.ACTIVE);
        assertNotNull(users);
    }

    ;
}
