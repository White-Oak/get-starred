package me.oak.getstarred.network.requests;

import me.oak.getstarred.server.replies.RegisterReply;
import retrofit.Call;
import retrofit.http.*;

/**
 *
 * @author White Oak
 */
public interface RegisterService {

    @FormUrlEncoded
    @POST("/users/")
    public Call<RegisterReply> register(@Field("login") String login, @Field("password") String password);
}
