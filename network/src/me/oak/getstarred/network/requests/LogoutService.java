package me.oak.getstarred.network.requests;

import me.oak.getstarred.server.replies.PlainReply;
import retrofit.Call;
import retrofit.http.*;

/**
 *
 * @author White Oak
 */
public interface LogoutService {

    @FormUrlEncoded
    @DELETE("sessions/")
    public Call<PlainReply> logout(@Field("digest") String digest);
}
