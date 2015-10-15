package me.oak.getstarred.server.chat;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor @Getter public class Client {

    private final Connection connection;
    private final int id;

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 17 * hash + this.id;
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
	final Client other = (Client) obj;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

}
