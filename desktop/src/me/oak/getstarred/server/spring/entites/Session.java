package me.oak.getstarred.server.spring.entites;

import java.sql.Timestamp;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author White Oak
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Setter
@Table(indexes = {@Index(columnList = "digest", unique = true)})
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private User user;

    private String digest;
    private Timestamp expiresTimestamp;

    public Session(User user, String digest) {
	this.user = user;
	this.digest = digest;
//	this.expiresTime = expiresTime;
    }
}
