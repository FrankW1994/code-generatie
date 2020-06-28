package io.swagger.dao;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.threeten.bp.LocalDate;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepositoryUser extends CrudRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstname = :firstname, u.lastname = :lastname, u.email = :email, u.phone = :phone, u.birthdate = :birthdate, u.rank = :rank, u.status = :status  WHERE u.id = :userId")
    void updateUser(@Param("userId") Integer userId, @Param("firstname") String firstname, @Param("lastname") String lastname, @Param("email") String email, @Param("phone") String phone, @Param("birthdate") String birthdate, @Param("rank") User.RankEnum rank, @Param("status") User.StatusEnum status);

    @Query("select u from User u where u.email =:email and u.password = :password")
    User findUserByUserCredentials(@Param("email") String username, @Param("password") String password);

    @Query("select u from User u where u.email =:email")
    User findByUserName(@Param("email") String username);

    @Query("select u from User u where u.firstname = :firstname")
    List<User> findByFirstname(@Param("firstname") String firstname);

    @Query("select u from User u where u.lastname = :lastname")
    List<User> findByLastname(@Param("lastname") String lastname);

    @Query("select u from User u where u.rank = :rank")
    List<User> findByRank(@Param("rank") User.RankEnum rank);

    @Query("select u from User u where u.status = :status")
    List<User> findByStatus(@Param("status") User.StatusEnum status);
}