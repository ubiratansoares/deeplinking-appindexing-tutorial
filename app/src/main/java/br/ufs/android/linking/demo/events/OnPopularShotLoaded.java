package br.ufs.android.linking.demo.events;

import java.util.List;

import br.ufs.android.linking.demo.dto.DribbbleShot;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class OnPopularShotLoaded {

    List<DribbbleShot> popularShots;
    int currentPage;

    public OnPopularShotLoaded(List<DribbbleShot> popularShots, int currentPage) {
        this.popularShots = popularShots;
        this.currentPage = currentPage;
    }

    public List<DribbbleShot> getPopularShots() {
        return popularShots;
    }

    public int getCurrentPage() {
        return currentPage;
    }


}
