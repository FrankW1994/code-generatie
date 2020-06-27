package io.swagger.service;

import io.swagger.dao.RepositoryUser;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserApiService {

    User userError = new User();
    @Autowired
    private RepositoryUser repositoryUser;

    public UserApiService() {
    }

    public List<User> getUsersWithFilters(String firstname, String lastname, String rank, String status) {
        List<User> userList = new ArrayList<>();
        if ((firstname != null) && !(firstname.equals(""))) {
            userList.addAll(getUsersFromFirstName(firstname));
/*            for (User u : getUsersFromFirstName(firstname)) {
                userList.add(u);
            }*/
        }
        if ((lastname != null) && !(lastname.equals(""))) {
            for (User u : getUsersFromLastName(lastname)) {
                if (!userList.contains(u))
                    userList.add(u);
            }
        }
        if ((rank != null) && !(rank.equals(""))) {
            for (User u : getUsersFromRank(rank)) {
                if (!userList.contains(u))
                    userList.add(u);
            }
        }
        if ((status != null) && !(status.equals(""))) {
            for (User u : getUsersFromStatus(status)) {
                if (!userList.contains(u))
                    userList.add(u);
            }
        }
        if (userList.size() == 0) {
            userList = getUsers();
        }
        return userList;
    }

    private List<User> getUsers() {
        return (List<User>) repositoryUser.findAll();
    }

    private List<User> getUsersFromFirstName(String firstname) {
        return repositoryUser.findByFirstname(firstname);
    }

    private List<User> getUsersFromLastName(String lastname) {
        return repositoryUser.findByLastname(lastname);

    }

    private List<User> getUsersFromRank(String rank) {
        User.RankEnum rankEnum = User.RankEnum.valueOf(rank.toUpperCase());
        return repositoryUser.findByRank(rankEnum);
    }

    private List<User> getUsersFromStatus(String status) {
        User.StatusEnum statusEnum = User.StatusEnum.valueOf(status.toUpperCase());
        return repositoryUser.findByStatus(statusEnum);
    }

    private void checkFirstOrLastName(String name) throws Exception {
        boolean valid = name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");

        if (!valid)
            throw new Exception("The first or lastname is not valid!");
    }

    private void checkEmail(String email) throws Exception {
        boolean valid = email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

        if (!valid)
            throw new Exception("The Email is not valid!");

    }

    private void checkPassword(String password) throws Exception {
        boolean valid = password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");

        if (!valid)
            throw new Exception("Make sure the password at least contains 1 uppercase, 1 digit, and is 8 charactars long!");
    }

    private void checkPhoneNumber(String phone) throws Exception {
        boolean valid = phone.matches("^((\\+|00(\\s|\\s?\\-\\s?)?)31(\\s|\\s?\\-\\s?)?(\\(0\\)[\\-\\s]?)?|0)[1-9]((\\s|\\s?\\-\\s?)?[0-9])((\\s|\\s?-\\s?)?[0-9])((\\s|\\s?-\\s?)?[0-9])\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]$");

        if (!valid)
            throw new Exception("The Phone number is invalid");

    }

    private void checkBirthDate(String date) throws Exception {
        boolean valid = date.matches("^([0-2][0-9]|(3)[0-1])(-)(((0)[0-9])|((1)[0-2]))(-)(19|20)\\d{2}$");

        if (!valid)
            throw new Exception("The birthdate is invalid");
    }


    public User postUser(User user) throws Exception {

        checkFirstOrLastName(user.getFirstname());
        checkFirstOrLastName(user.getLastname());
        checkEmail(user.getEmail());
        checkPassword(user.getPassword());
        checkPhoneNumber(user.getPhone());
        checkBirthDate(user.getBirthdate());

        repositoryUser.save(user);
        return user;
    }

    public User getById(Long userId) {
        return repositoryUser.findById(userId).get();
    }

    public HttpStatus delete(String userId) {
        Long userIdLong = null;
        try {
            userIdLong = Long.parseLong(userId);
        } catch (NumberFormatException nfe) {
        }
        if (userIdLong != null) {
            try {
                repositoryUser.deleteById(userIdLong);
                return HttpStatus.OK;
            } catch (Exception e) {
                return HttpStatus.NOT_FOUND;
            }
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public User update(String userId, User body) {
        try {
            body.setId(Long.parseLong(userId));
            repositoryUser.updateUser(body.getId(), body.getFirstname(), body.getLastname(), body.getEmail(), body.getPhone(), body.getBirthdate(), body.getRank(), body.getStatus());
        } catch (NumberFormatException nfe) {
            return userError;
        }
        return userError;
    }

}
