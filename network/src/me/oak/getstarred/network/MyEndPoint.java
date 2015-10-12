package me.oak.getstarred.network;

import me.oak.getstarred.server.replies.RegisterReply;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 *
 * @author White Oak
 */
public interface MyEndPoint {

    @POST("/users")
    public RegisterReply register(@Body String login, @Body String password);
}
