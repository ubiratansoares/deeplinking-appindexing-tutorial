package br.ufs.android.linking.demo.presentation.views;

import java.util.List;

import br.ufs.android.linking.demo.dto.DribbbleShot;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public interface PopularShotsView extends LoadingItemsView {

    void dribbbleShotsLoaded(List<DribbbleShot> shots);

}

