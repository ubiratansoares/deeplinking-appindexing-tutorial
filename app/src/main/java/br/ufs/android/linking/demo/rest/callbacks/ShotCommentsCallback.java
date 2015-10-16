package br.ufs.android.linking.demo.rest.callbacks;

import java.util.List;

import br.ufs.android.linking.demo.dto.ShotComment;
import br.ufs.android.linking.demo.events.OnShotCommentsLoaded;
import de.greenrobot.event.EventBus;
import retrofit.client.Response;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotCommentsCallback extends ErrorHandledCallback<List<ShotComment>> {


    @Override public void success(List<ShotComment> shotComments, Response response) {
        EventBus.getDefault().post(new OnShotCommentsLoaded(shotComments));
    }
}
