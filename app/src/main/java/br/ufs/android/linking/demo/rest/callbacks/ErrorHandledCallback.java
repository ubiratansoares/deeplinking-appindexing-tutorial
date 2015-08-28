package br.ufs.android.linking.demo.rest.callbacks;

import br.ufs.android.linking.demo.events.OnNetworkError;
import br.ufs.android.linking.demo.events.OnRequestError;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public abstract class ErrorHandledCallback<T> implements Callback<T> {

    private EventBus bus = EventBus.getDefault();

    @Override public void failure(RetrofitError error) {
        final RetrofitError.Kind kind = error.getKind();

        switch (kind) {
            case HTTP:
            case CONVERSION:
                bus.post(new OnRequestError());
                return;

            case NETWORK:
                bus.post(new OnNetworkError());
                return;

            case UNEXPECTED:
                throw new IllegalStateException(error.getCause());

        }
    }
}
