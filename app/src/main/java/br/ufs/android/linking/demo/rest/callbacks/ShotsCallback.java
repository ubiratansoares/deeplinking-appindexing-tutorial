package br.ufs.android.linking.demo.rest.callbacks;

import java.util.List;

import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.events.OnPopularShotLoaded;
import de.greenrobot.event.EventBus;
import retrofit.client.Response;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotsCallback extends ErrorHandledCallback<List<DribbbleShot>> {

    @Override public void success(List<DribbbleShot> dribbbleShots, Response response) {
        EventBus.getDefault().post(new OnPopularShotLoaded(dribbbleShots, 0));
    }
}