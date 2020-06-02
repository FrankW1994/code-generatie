package io.swagger.dao;



import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends CrudRepository<User, Long> {
}