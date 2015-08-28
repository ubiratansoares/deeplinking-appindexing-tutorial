package br.ufs.android.linking.demo.presentation.presenters;

import br.ufs.android.linking.demo.dto.ErrorMessageType;
import br.ufs.android.linking.demo.events.OnNetworkError;
import br.ufs.android.linking.demo.events.OnPopularShotLoaded;
import br.ufs.android.linking.demo.events.OnRequestError;
import br.ufs.android.linking.demo.interactor.RESTInteractor;
import br.ufs.android.linking.demo.presentation.views.PopularShotsView;
import de.greenrobot.event.EventBus;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class PopularShotsPresenter {

    private PopularShotsView view;
    private RESTInteractor interactor;

    public PopularShotsPresenter(PopularShotsView view) {
        this.view = view;
        interactor = new RESTInteractor();
    }

    public void resume() {
        EventBus.getDefault().register(this);
    }

    public void pause() {
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(OnPopularShotLoaded event) {
        view.dribbbleShotsLoaded(event.getPopularShots());
        view.hideLoadingIndicator();
        view.hideLoadingIndicator();
    }

    public void onEvent(OnRequestError event) {
        view.showErrorMessage(ErrorMessageType.REQUEST_ERROR);
        view.hideLoadingIndicator();
    }

    public void onEvent(OnNetworkError event) {
        view.showErrorMessage(ErrorMessageType.NETWORK_ERROR);
        view.hideLoadingIndicator();
    }

    public void requireMoreShots(int page) {
        interactor.fetchPopularShots(page);
        view.hideBlankstateMessage();
        view.showLoadingIndicator();
    }
}
