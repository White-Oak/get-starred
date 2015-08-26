package me.oak.getstarred.server.entites;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author White Oak
 */
public interface SessionRepository extends CrudRepository<Session, Integer> {

    public Optional<Session> findByDigest(String digest);
}
