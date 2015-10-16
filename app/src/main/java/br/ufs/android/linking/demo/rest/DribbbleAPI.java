package br.ufs.android.linking.demo.rest;

import java.util.List;

import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.dto.ShotComment;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public interface DribbbleAPI {

    @GET("/shots") void popularShots(
            @Query("page") int page,
            Callback<List<DribbbleShot>> callback
            );

    @GET("/shots/{shotId}/comments/") void commentsForShot(
            @Path("shotId") int shotId,
            Callback<List<ShotComment>> callback
    );

    @GET("/shots/{shotId}") void shotDetails(
            @Path("shotId") String shotId,
            Callback<DribbbleShot> callback
    );

    @GET("/users/{userId}/shots") void shotsForPlayer(
            @Path("userId") String shotId,
            Callback<List<DribbbleShot>> callback
    );
}