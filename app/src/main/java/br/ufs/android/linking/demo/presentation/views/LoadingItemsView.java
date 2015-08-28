package br.ufs.android.linking.demo.presentation.views;

import br.ufs.android.linking.demo.dto.ErrorMessageType;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public interface LoadingItemsView {

    void hideLoadingIndicator();

    void showErrorMessage(ErrorMessageType type);

    void hideBlankstateMessage();

    void showLoadingIndicator();

}