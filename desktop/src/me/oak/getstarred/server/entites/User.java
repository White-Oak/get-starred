package me.oak.getstarred.server.entites;

import javax.persistence.*;
import lombok.*;

/**
 *
 * @author White Oak
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private boolean lookingForMatch;
    private boolean inLobby;
    private boolean inMatch;
    private String password_digest;
    @OneToOne(mappedBy = "user") private Session currentSession;
    @OneToOne(mappedBy = "user") private Deck currentDeck;

    public User(String login, String password_digest) {
	this.login = login;
	this.password_digest = password_digest;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 97 * hash + this.id;
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final User other = (User) obj;
	return this.id == other.id;
    }

    @Override
    public String toString() {
	return "User{" + "id=" + id + ", login=" + login + ", lookingForMatch=" + lookingForMatch + ", inLobby=" + inLobby + '}';
    }

}
