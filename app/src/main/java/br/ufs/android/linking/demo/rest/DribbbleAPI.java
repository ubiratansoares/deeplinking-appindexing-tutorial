package br.ufs.android.linking.demo.rest;

import br.ufs.android.linking.demo.rest.payloads.CommentsForShotPayload;
import br.ufs.android.linking.demo.rest.payloads.ShotsPayload;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public interface DribbbleAPI {

    @GET("/shots/popular") void popularShots(
            @Query("page") int page,
            Callback<ShotsPayload> callback
    );

    @GET("/shots/{shotId}/comments/") void commentsForShot(
            @Path("shotId") int shotId,
            Callback<CommentsForShotPayload> callback
    );

}