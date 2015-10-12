package me.oak.getstarred.network.requests;

import me.oak.getstarred.server.replies.LoginReply;
import retrofit.Call;
import retrofit.http.*;

/**
 *
 * @author White Oak
 */
public interface LoginService {

    @FormUrlEncoded
    @POST("/sessions/")
    public Call<LoginReply> login(@Field("login") String login, @Field("password") String password);
}
