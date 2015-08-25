package me.oak.getstarred.server.spring.entites;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author White Oak
 */
public interface SessionRepository extends CrudRepository<Session, Integer> {

    public List<Session> findByDigest(String digest);
}
