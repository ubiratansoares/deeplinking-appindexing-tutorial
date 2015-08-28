package br.ufs.android.linking.demo.rest;

import br.ufs.android.linking.demo.BuildConfig;
import retrofit.RestAdapter;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class RestAdapterProvider {

    private static final RestAdapterProvider INSTANCE = new RestAdapterProvider();

    private RestAdapter adapter;

    private RestAdapterProvider() {
        adapter = new RestAdapter.Builder()
                .setEndpoint("http://api.dribbble.com")
                .setLogLevel(defineLogLevel())
                .build();
    }

    public static RestAdapterProvider instance() {
        return INSTANCE;
    }

    public RestAdapter getAdapter() {
        return adapter;
    }

    private RestAdapter.LogLevel defineLogLevel() {
        return BuildConfig.DEBUG ?
                RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE;
    }

}
