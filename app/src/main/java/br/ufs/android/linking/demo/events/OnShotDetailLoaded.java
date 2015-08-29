package br.ufs.android.linking.demo.events;

import br.ufs.android.linking.demo.dto.DribbbleShot;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class OnShotDetailLoaded {

    DribbbleShot shot;

    public OnShotDetailLoaded(DribbbleShot shot) {
        this.shot = shot;
    }

    public DribbbleShot getShot() {
        return shot;
    }
}
