package br.ufs.android.linking.demo.rest;

import retrofit.RequestInterceptor;

/**
 * Created by ubiratansoares on 10/15/15.
 */

public class DribbbleTokenInterceptor implements RequestInterceptor {

    public static final String ACCESS_TOKEN = null;

    @Override public void intercept(RequestFacade request) {
        if(ACCESS_TOKEN == null)
            throw new IllegalArgumentException
                    ("Use this demo with a valid Dribbble Access Token!!");

        request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
    }
}
