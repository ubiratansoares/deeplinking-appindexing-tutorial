package br.ufs.android.linking.demo.events;

import java.util.List;

import br.ufs.android.linking.demo.dto.ShotComment;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class OnShotCommentsLoaded {

    private List<ShotComment> comments;

    public OnShotCommentsLoaded(List<ShotComment> comments) {
        this.comments = comments;
    }

    public List<ShotComment> getComments() {
        return comments;
    }
}
