package br.ufs.android.linking.demo.presentation.presenters;

import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.dto.ErrorMessageType;
import br.ufs.android.linking.demo.events.OnNetworkError;
import br.ufs.android.linking.demo.events.OnRequestError;
import br.ufs.android.linking.demo.events.OnShotCommentsLoaded;
import br.ufs.android.linking.demo.interactor.RESTInteractor;
import br.ufs.android.linking.demo.presentation.views.ShotDetailsView;
import de.greenrobot.event.EventBus;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotDetailsPresenter {

    private RESTInteractor interactor;
    private ShotDetailsView view;

    public ShotDetailsPresenter(ShotDetailsView view) {
        this.view = view;
        interactor = new RESTInteractor();
    }

    public void resume() {
        EventBus.getDefault().register(this);
        view.fillDribbblePlayerInfo();

    }

    public void pause() {
        EventBus.getDefault().unregister(this);
    }

    public void fetchCommentsForShot(DribbbleShot shot) {
        interactor.fetchCommentsForShot(shot);
        view.showLoadingIndicator();
        view.hideBlankstateMessage();
    }

    public void onEvent(OnRequestError event) {
        view.showErrorMessage(ErrorMessageType.REQUEST_ERROR);
        view.hideLoadingIndicator();
    }

    public void onEvent(OnNetworkError event) {
        view.showErrorMessage(ErrorMessageType.NETWORK_ERROR);
        view.hideLoadingIndicator();
    }

    public void onEvent(OnShotCommentsLoaded event) {
        view.shotCommentsLoaded(event.getComments());
        view.hideLoadingIndicator();
    }
}
