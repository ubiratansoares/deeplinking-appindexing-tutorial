package br.ufs.android.linking.demo.rest.payloads;

import java.util.List;

import br.ufs.android.linking.demo.dto.ShotComment;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class CommentsForShotPayload {

    List<ShotComment> comments;

    public List<ShotComment> getComments() {
        return comments;
    }
}