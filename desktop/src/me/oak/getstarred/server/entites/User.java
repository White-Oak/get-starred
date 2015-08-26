package me.oak.getstarred.server.entites;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password_digest;
    @OneToOne(mappedBy = "user") private Session currentSession;

    public User(String login, String password_digest) {
	this.login = login;
	this.password_digest = password_digest;
    }

}
