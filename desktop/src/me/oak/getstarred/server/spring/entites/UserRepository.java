package me.oak.getstarred.server.spring.entites;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author White Oak
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByLogin(String login);
}
