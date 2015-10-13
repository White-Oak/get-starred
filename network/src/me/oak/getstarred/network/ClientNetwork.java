package me.oak.getstarred.network;

import com.google.gson.Gson;
import java.io.IOException;
import me.oak.getstarred.network.requests.*;
import me.oak.getstarred.server.replies.*;
import me.whiteoak.minlog.Log;
import retrofit.*;

/**
 *
 * @author White Oak
 */
public final class ClientNetwork {

    private final static String BASE = "http://localhost:8080";
//    private final static String BASE = "http://get-starred-server-whiteoak.c9.io/";
    Retrofit retrofit = new Retrofit.Builder()
	    .baseUrl(BASE)
	    .addConverterFactory(GsonConverterFactory.create(new Gson()))
	    .build();
    private final Gson gson = new Gson();

    public RegisterReply register(final String login, final String password) {
	final String category = "[REG] ";
	final RegisterService registerService = retrofit.create(RegisterService.class);
	final Call<RegisterReply> registerCall = registerService.register(login, password);
	return deal(category, registerCall);
    }

    private <T> T deal(String category, Call<T> call) {
	try {
	    final Response<T> response = call.execute();
	    int statusCode = response.code();
	    if (statusCode == 200) {
		return response.body();
	    } else {
		logResponseError(category, response);
	    }

	} catch (IOException ex) {
	    logIOError(category, call, ex);
	}
	return null;
    }

    private void logIOError(String category, Call call, Throwable ex) {
	Log.error("network", category + "While trying to request: \n" + call, ex);
    }

    private void logResponseError(String category, Response response) {
	Log.error("network", category + "Response didn't quite hit the mark: \n" + response.message());
    }

    public LoginReply login(String login, String password) {
	final String category = "[LGI] ";
	LoginService loginService = retrofit.create(LoginService.class);
	final Call<LoginReply> call = loginService.login(login, password);
	return deal(category, call);
    }

    public Reply logout(String digest) {
	final String category = "[LGO] ";
	LogoutService logoutService = retrofit.create(LogoutService.class);
	final Call<PlainReply> call = logoutService.logout(digest);
	return deal(category, call);
    }

}
