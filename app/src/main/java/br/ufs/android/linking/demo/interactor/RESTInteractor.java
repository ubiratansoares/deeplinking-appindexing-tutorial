package br.ufs.android.linking.demo.interactor;

import java.util.HashMap;
import java.util.Map;

import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.rest.DribbbleAPI;
import br.ufs.android.linking.demo.rest.RestAdapterProvider;
import br.ufs.android.linking.demo.rest.callbacks.ShotCommentsCallback;
import br.ufs.android.linking.demo.rest.callbacks.ShotDetailsCallback;
import br.ufs.android.linking.demo.rest.callbacks.ShotsCallback;
import retrofit.RestAdapter;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class RESTInteractor {

    private DribbbleAPI api;

    public RESTInteractor() {
        RestAdapter adapter = RestAdapterProvider.instance().getAdapter();
        api = adapter.create(DribbbleAPI.class);
    }

    public void fetchPopularShots(int page) {
        final Map<String, Object> args = new HashMap<>();
        api.popularShots(page, new ShotsCallback());
    }

    public void fetchCommentsForShot(DribbbleShot shot) {
        api.commentsForShot(shot.getId(), new ShotCommentsCallback());
    }

    public void fetchShotDetails(String shotId) {
        api.shotDetails(shotId, new ShotDetailsCallback());
    }

    public void fetchShotsForPlayer(String playerId) {
        api.shotsForPlayer(playerId, new ShotsCallback());
    }

}
