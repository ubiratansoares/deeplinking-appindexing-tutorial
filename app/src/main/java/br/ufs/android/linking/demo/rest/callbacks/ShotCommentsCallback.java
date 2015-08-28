package br.ufs.android.linking.demo.rest.callbacks;

import java.util.List;

import br.ufs.android.linking.demo.dto.ShotComment;
import br.ufs.android.linking.demo.events.OnShotCommentsLoaded;
import br.ufs.android.linking.demo.rest.payloads.CommentsForShotPayload;
import de.greenrobot.event.EventBus;
import retrofit.client.Response;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotCommentsCallback extends ErrorHandledCallback<CommentsForShotPayload> {

    @Override public void success(CommentsForShotPayload payload, Response response) {
        final List<ShotComment> comments = payload.getComments();
        EventBus.getDefault().post(new OnShotCommentsLoaded(comments));
    }
}
