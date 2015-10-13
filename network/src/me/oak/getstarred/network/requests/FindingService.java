package me.oak.getstarred.network.requests;

import me.oak.getstarred.server.replies.FindReply;
import retrofit.Call;
import retrofit.http.*;

/**
 *
 * @author White Oak
 */
public interface FindingService {

    @FormUrlEncoded
    @POST("/find/")
    public Call<FindReply> find(@Field("digest") String digest);
}
