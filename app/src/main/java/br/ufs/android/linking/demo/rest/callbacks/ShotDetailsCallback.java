package br.ufs.android.linking.demo.rest.callbacks;

import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.events.OnShotDetailLoaded;
import de.greenrobot.event.EventBus;
import retrofit.client.Response;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotDetailsCallback extends ErrorHandledCallback<DribbbleShot> {

    @Override public void success(DribbbleShot dribbbleShot, Response response) {
        EventBus.getDefault().post(new OnShotDetailLoaded(dribbbleShot));
    }
}
