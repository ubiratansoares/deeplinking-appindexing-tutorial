package br.ufs.android.linking.demo.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

import br.ufs.android.linking.demo.R;
import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.events.OnShotDetailLoaded;
import br.ufs.android.linking.demo.interactor.RESTInteractor;
import br.ufs.android.linking.demo.ui.fragments.ShotDetailsFragment;
import br.ufs.android.linking.demo.ui.util.AppIndexingUtils;
import br.ufs.android.linking.demo.ui.util.URIIndexListener;
import de.greenrobot.event.EventBus;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotDetailsActivity extends BaseActivity {

    private static final String TAG = ShotDetailsActivity.class.getSimpleName();

    private static final String PACKAGE = "br.ufs.android.linking.demo";
    private static final String SCHEMA = "https";
    private static final String HOST = "dribbble.com";

    private static final String EXTRA_SHOT = "extra.shot";

    private DribbbleShot shot;
    private GoogleApiClient gmsClient;

    public static Intent lauchIntent(Activity from, DribbbleShot shot) {
        final Intent i = new Intent(from, ShotDetailsActivity.class);
        i.putExtra(EXTRA_SHOT, shot);
        return i;
    }

    @Override protected int layoutResource() {
        return R.layout.activity_fragment_container;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gmsClient = new GoogleApiClient.Builder(this)
                .addApi(AppIndex.APP_INDEX_API)
                .build();

        onNewIntent(getIntent());

        EventBus.getDefault().register(this);

        if (getIntent().hasExtra(EXTRA_SHOT)) {
            shot = (DribbbleShot) getIntent().getSerializableExtra(EXTRA_SHOT);
            showShotDetails(shot);
        } else {
            verifyDeepLinking();
        }

    }

    @Override public void onStart() {
        super.onStart();
        if (gmsClient != null) {
            gmsClient.connect();
            if (shot != null) {
                recordShotViewed();
            }
        }
        super.onStart();
    }


    @Override public void onStop() {
        if (shot != null && gmsClient != null && gmsClient.isConnected()) {
            recordShotViewEnded();
            gmsClient.disconnect();
        }

        super.onStop();

    }

    @Override protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEvent(OnShotDetailLoaded event) {
        shot = event.getShot();
        showShotDetails(event.getShot());
    }

    private void showShotDetails(DribbbleShot shot) {
        setTitle(shot.getTitle());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ShotDetailsFragment.with(shot))
                .commit();
    }

    private void verifyDeepLinking() {
        final Uri inputURI = getIntent().getData();
        if (inputURI != null) {
            final String url = inputURI.toString();
            String shotId = AppIndexingUtils.parseShotId(url);

            if (shotId != null) {
                retriveShotWithId(shotId);
            }

        }
    }

    private void retriveShotWithId(String shotId) {
        RESTInteractor interactor = new RESTInteractor();
        interactor.fetchShotDetails(shotId);
    }

    private void recordShotViewed() {
        Action viewAction = AppIndexingUtils.getActionForShot(shot);
        PendingResult<Status> result = AppIndex.AppIndexApi.start(gmsClient, viewAction);
        result.setResultCallback(new URIIndexListener());
    }

    private void recordShotViewEnded() {
        Action viewAction = AppIndexingUtils.getActionForShot(shot);
        PendingResult<Status> result = AppIndex.AppIndexApi.end(gmsClient, viewAction);
        result.setResultCallback(new URIIndexListener());
    }

}