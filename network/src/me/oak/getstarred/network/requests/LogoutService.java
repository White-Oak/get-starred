package me.oak.getstarred.network.requests;

import me.oak.getstarred.server.replies.Reply;
import retrofit.Call;
import retrofit.http.*;

/**
 *
 * @author White Oak
 */
public interface LogoutService {

    @FormUrlEncoded
    @DELETE("sessions/")
    public Call<Reply> logout(@Field("digest") String digest);
}
