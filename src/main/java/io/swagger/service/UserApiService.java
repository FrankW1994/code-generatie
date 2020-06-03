package io.swagger.service;

import io.swagger.dao.RepositoryUser;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserApiService {

    @Autowired
    private RepositoryUser repositoryUser;

    public UserApiService(){}
    User userError = new User(500L, "Error", "Error", "Error 500", "Error 500", "Error 500", "Error 500", "Error 500", User.RankEnum.CUSTOMER, User.StatusEnum.BLOCKED);

    public List<User> getUsers(){
        return (List<User>) repositoryUser.findAll();
    }

    public User postUser(User user){
        repositoryUser.save(user);
        return user;
    }

    public User getById(Long userId) {
        return repositoryUser.findOne(userId);
    }

    public HttpStatus delete(String userId){
        Long userIdLong = null;
        try {
            userIdLong = Long.parseLong(userId);
        }
        catch (NumberFormatException nfe){
            System.out.println(nfe);
        }
        if (!userIdLong.equals(null)) {
            repositoryUser.delete(userIdLong);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public User update(String userId, User userNewDetails){
        Long userIdLong;
        try {
            userIdLong = Long.parseLong(userId);
        }
        catch (NumberFormatException nfe){
            System.out.println(nfe);
            return userError;
        }
//        User userUpdated = repositoryUser.update(userIdLong, userNewDetails);
        return userError;
    }

}
