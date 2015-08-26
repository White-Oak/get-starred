package me.oak.getstarred.server.entites;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author White Oak
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByLogin(String login);
}
