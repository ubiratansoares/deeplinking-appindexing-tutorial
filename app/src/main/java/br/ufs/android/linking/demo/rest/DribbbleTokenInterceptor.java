package br.ufs.android.linking.demo.rest;

import retrofit.RequestInterceptor;

/**
 * Created by ubiratansoares on 10/15/15.
 */

public class DribbbleTokenInterceptor implements RequestInterceptor {

    public static final String ACCESS_TOKEN =
            "bc1b249502dece98d6c157b3e0274e97b9f43a04d81b76c6ccbadfc7fff63362";

    @Override public void intercept(RequestFacade request) {
        request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
    }
}
