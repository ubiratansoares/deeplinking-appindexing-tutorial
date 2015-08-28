package br.ufs.android.linking.demo.rest.callbacks;

import java.util.List;

import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.events.OnPopularShotLoaded;
import br.ufs.android.linking.demo.rest.payloads.ShotsPayload;
import de.greenrobot.event.EventBus;
import retrofit.client.Response;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class PopularShotsCallback extends ErrorHandledCallback<ShotsPayload> {

    @Override public void success(ShotsPayload payload, Response response) {
        final List<DribbbleShot> shots = payload.getShots();
        final int currentPage = payload.getPage();
        EventBus.getDefault().post(new OnPopularShotLoaded(shots, currentPage));
    }

}