package me.oak.getstarred.server.entites;

import com.esotericsoftware.kryonet.Connection;
import java.util.Date;
import lombok.*;

/**
 *
 * @author White Oak
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Session {

    private long id;
    private User user;
    private String digest;
    private Date expiresTimestamp;
    private Connection connection;

    public Session(User user, String digest, Date expiresTimestamp, Connection connection) {
	this.user = user;
	this.digest = digest;
	this.expiresTimestamp = expiresTimestamp;
	this.connection = connection;
    }

    @Override
    public String toString() {
	return "Session{" + "id=" + id + ", digest=" + digest + ", expiresTimestamp=" + expiresTimestamp + '}';
    }

}
