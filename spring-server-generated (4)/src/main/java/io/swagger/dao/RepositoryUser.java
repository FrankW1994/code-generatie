package io.swagger.dao;



import org.springframework.data.jpa.repository.Modifying;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepositoryUser extends CrudRepository<User, Long> {

    @Query("INSERT INTO User (id, firstname, lastname, email, password, phone, birthday, registrationdate, rank, status) VALUES" +
            "(:id, :firstname, :lastname, :email, :password, :phone, :birthday, :registrationdate, :rank, :status)")
    List<User> PostUser(@Param("id") long id, @Param("firstname") String firstname, @Param("lastname") String lastname,
                        @Param("email") String email, @Param("password") String password, @Param("phone") String phone,
                        @Param("birthday") String birthday, @Param("registrationdate") String registrationdate,
                        @Param("rank") User.RankEnum rank, @Param("status") User.StatusEnum status);

//    @Query("select t from Transaction t where t.nameSender = :username")
//    List<User> findAllWithUsername(@Param("username") String username);
//
//    @Query(value = "select t from Transaction t join User u where u.id =:userId", nativeQuery = true)
//    List<User> getTransactionsFromUserId(@Param("userId") String userId);
//
//    @Query("select t from Transaction t where t.ibanSender = :iban or t.ibanReceiver =:iban")
//    List<User> getTransactionsFromIBAN(@Param("iban") String iban);
//
//    @Query("select t from Transaction t where t.transferAmount =:transactionAmount")
//    List<User> getTransactionsFromAmount(@Param("transactionAmount") Double transactionAmount);
}