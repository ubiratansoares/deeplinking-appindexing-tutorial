package br.ufs.android.linking.demo.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.ufs.android.linking.demo.R;
import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.dto.ErrorMessageType;
import br.ufs.android.linking.demo.presentation.presenters.PopularShotsPresenter;
import br.ufs.android.linking.demo.presentation.views.PopularShotsView;
import br.ufs.android.linking.demo.ui.activities.ShotDetailsActivity;
import br.ufs.android.linking.demo.ui.adapters.PopularShotsListAdapter;
import br.ufs.android.linking.demo.ui.util.EndlessScrollListener;
import br.ufs.android.linking.demo.ui.util.RecyclerViewItemClickCompat;
import butterknife.Bind;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class PopularShotsFragment extends BaseFragment
        implements PopularShotsView, RecyclerViewItemClickCompat.OnItemClickListener {

    private static final int FIRST_SHOTS_PAGE = 0;

    @Bind(R.id.recyclerview_shots) RecyclerView shotsRecyclerView;
    @Bind(R.id.progressbar_loading_shots) ProgressBar loadingBar;
    @Bind(R.id.txt_blankstate) TextView blankstateLabel;

    private PopularShotsListAdapter adapter;
    private PopularShotsPresenter shotsPresenter;

    @Override protected int layoutResource() {
        return R.layout.fragment_list_shots;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        shotsPresenter = new PopularShotsPresenter(this);
        shotsPresenter.requireMoreShots(FIRST_SHOTS_PAGE);
    }

    @Override public void onResume() {
        shotsPresenter.resume();
        super.onResume();
    }

    @Override public void onPause() {
        shotsPresenter.pause();
        super.onPause();
    }

    @Override public void showLoadingIndicator() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideLoadingIndicator() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override public void showErrorMessage(ErrorMessageType type) {
        blankstateLabel.setVisibility(View.VISIBLE);

        if (adapter.getItemCount() == 0) {

            switch (type) {
                case NETWORK_ERROR:
                    blankstateLabel.setText(R.string.label_error_network);
                    break;

                case REQUEST_ERROR:
                    blankstateLabel.setText(R.string.label_error_request);
                    break;
            }
        } else {
            Toast.makeText(getActivity(), R.string.error_message_pagination, Toast.LENGTH_SHORT).show();
        }


    }


    @Override public void hideBlankstateMessage() {
        blankstateLabel.setVisibility(View.GONE);
    }

    @Override public void dribbbleShotsLoaded(List<DribbbleShot> shots) {
        adapter.add(shots);
    }

    @Override public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        final DribbbleShot shot = adapter.getItem(position);
        startActivity(ShotDetailsActivity.lauchIntent(getActivity(), shot));
    }

    private void setupRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new PopularShotsListAdapter(getActivity());

        shotsRecyclerView.setLayoutManager(manager);

        shotsRecyclerView.addOnScrollListener(new EndlessScrollListener(manager) {
            @Override public void onLoadMore(int currentPage) {
                shotsPresenter.requireMoreShots(currentPage);
            }
        });

        RecyclerViewItemClickCompat.addTo(shotsRecyclerView).setOnItemClickListener(this);
        shotsRecyclerView.setAdapter(adapter);
    }

}
