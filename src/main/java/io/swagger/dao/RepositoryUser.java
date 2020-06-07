package io.swagger.dao;

import io.swagger.model.Account;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RepositoryUser extends CrudRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstname = :firstname, u.lastname = :lastname, u.email = :email, u.phone = :phone, u.birthdate = :birthdate, u.rank = :rank, u.status = :status  WHERE u.id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("firstname") String firstname, @Param("lastname") String lastname, @Param("email") String email, @Param("phone") String phone, @Param("birthdate") String birthdate, @Param("rank") User.RankEnum rank, @Param("status") User.StatusEnum status);
}