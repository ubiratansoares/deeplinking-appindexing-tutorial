package br.ufs.android.linking.demo.rest.payloads;

import java.util.List;

import br.ufs.android.linking.demo.dto.DribbbleShot;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotsPayload {

    int page;
    int perPage;
    int pages;
    int total;
    List<DribbbleShot> shots;

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getPages() {
        return pages;
    }

    public int getTotal() {
        return total;
    }

    public List<DribbbleShot> getShots() {
        return shots;
    }

}
