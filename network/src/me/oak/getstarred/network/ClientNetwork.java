package me.oak.getstarred.network;

import com.google.gson.Gson;
import com.sun.jersey.api.client.*;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import me.oak.getstarred.server.replies.*;
import me.whiteoak.minlog.Log;

/**
 *
 * @author White Oak
 */
public final class ClientNetwork {

    private final static String BASE = "http://localhost:8080/";
//    private final static String BASE = "http://get-starred-server-whiteoak.c9.io/";
    private final Client client = new Client();
    private final WebResource userResource = client.resource(BASE + "users/");
    private final WebResource sessionResource = client.resource(BASE + "sessions/");
    private final Gson gson = new Gson();

    public RegisterReply register(String login, String password) {
	final String category = "[REG] ";
	ClientResponse response = tryRegister(login, password);
	final Response.StatusType statusInfo = response.getStatusInfo();
	final int statusCode = statusInfo.getStatusCode();
	Log.debug("network", category + "Status: " + statusCode + statusInfo.getReasonPhrase());
	final String entity = response.getEntity(String.class);
	if (statusCode == 200) {
	    return gson.fromJson(entity, RegisterReply.class);
	} else {
	    Log.error("network", category + "Response didn't quite hit the mark: \n" + entity);
	    return null;
	}
    }

    private ClientResponse tryRegister(String login, String password) {
	MultivaluedMap formData = new MultivaluedMapImpl();
	formData.add("login", login);
	formData.add("password", password);
	return userResource.post(ClientResponse.class, formData);
    }

    public LoginReply login(String login, String password) {
	final String category = "[LGI] ";
	ClientResponse response = tryLogin(login, password);
	final Response.StatusType statusInfo = response.getStatusInfo();
	final int statusCode = statusInfo.getStatusCode();
	Log.debug("network", category + "Status: " + statusCode + statusInfo.getReasonPhrase());
	final String entity = response.getEntity(String.class);
	if (statusCode == 200) {
	    return gson.fromJson(entity, LoginReply.class);
	} else {
	    Log.error("network", category + "Response didn't quite hit the mark: \n" + entity);
	    return null;
	}
    }

    private ClientResponse tryLogin(String login, String password) {
	MultivaluedMap formData = new MultivaluedMapImpl();
	formData.add("login", login);
	formData.add("password", password);
	return sessionResource.post(ClientResponse.class, formData);
    }

    public Reply logout(String digest) {
	final String category = "[LGO] ";
	ClientResponse response = tryLogout(digest);
	final Response.StatusType statusInfo = response.getStatusInfo();
	final int statusCode = statusInfo.getStatusCode();
	Log.debug("network", category + "Status: " + statusCode + statusInfo.getReasonPhrase());
	final String entity = response.getEntity(String.class);
	if (statusCode == 200) {
	    return gson.fromJson(entity, Reply.class);
	} else {
	    Log.error("network", category + "Response didn't quite hit the mark: \n" + entity);
	    return null;
	}
    }

    private ClientResponse tryLogout(String digest) {
	return sessionResource.queryParam("digest", digest).delete(ClientResponse.class);
    }
}
