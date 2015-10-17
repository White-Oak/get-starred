package me.oak.getstarred.network.requests;

import me.oak.getstarred.server.replies.ReadyReply;
import retrofit.Call;
import retrofit.http.*;

/**
 *
 * @author White Oak
 */
public interface ReadyInterface {

    @FormUrlEncoded
    @POST("/ready/")
    public Call<ReadyReply> ready(@Field("digest") String digest);
}
