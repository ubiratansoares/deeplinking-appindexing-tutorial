package br.ufs.android.linking.demo.ui.util;

import android.util.Log;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by ubiratansoares on 10/17/15.
 */


public class URIIndexListener implements ResultCallback<Status> {

    @Override public void onResult(Status status) {
        if (status.isSuccess()) {
            Log.d("URLIndexer", "App Indexing API: SUCCESS");
        } else {
            Log.d("URLIndexer", "App Indexing API: ERROR");
        }
    }
}
