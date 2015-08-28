package br.ufs.android.linking.demo.presentation.views;

import java.util.List;

import br.ufs.android.linking.demo.dto.ShotComment;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public interface ShotDetailsView extends LoadingItemsView {

    void fillDribbblePlayerInfo();

    void shotCommentsLoaded(List<ShotComment> comments);

}
