package me.oak.getstarred.server.spring.entites;

import java.util.Date;
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

    @OneToOne private User user;

    private String digest;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresTimestamp;

    public Session(User user, String digest, Date expiresTimestamp) {
	this.user = user;
	this.digest = digest;
	this.expiresTimestamp = expiresTimestamp;
    }
}
