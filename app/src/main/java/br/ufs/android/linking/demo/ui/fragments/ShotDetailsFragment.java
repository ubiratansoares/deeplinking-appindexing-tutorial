package br.ufs.android.linking.demo.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufs.android.linking.demo.R;
import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.dto.DribbbleUser;
import br.ufs.android.linking.demo.dto.ErrorMessageType;
import br.ufs.android.linking.demo.dto.ShotComment;
import br.ufs.android.linking.demo.presentation.presenters.ShotDetailsPresenter;
import br.ufs.android.linking.demo.presentation.views.ShotDetailsView;
import br.ufs.android.linking.demo.ui.activities.PlayerDetailsActivity;
import br.ufs.android.linking.demo.ui.adapters.ShotCommentsAdapter;
import br.ufs.android.linking.demo.ui.util.CircularTransformation;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ubiratansoares on 8/28/15.
 */
public class ShotDetailsFragment extends BaseFragment implements ShotDetailsView {

    private static final String ARGUMENT_SHOT = "argument.shot";

    @Bind(R.id.recyclerview_shot_comments) RecyclerView commentsRecyclerView;
    @Bind(R.id.txt_player_name) TextView userNameLabel;
    @Bind(R.id.txt_location) TextView userLocationLabel;
    @Bind(R.id.img_dribble_player) ImageView dribbleUserImage;
    @Bind(R.id.progressbar_loading_comments) ProgressBar loadingBar;
    @Bind(R.id.txt_blankstate) TextView blankStateLabel;

    private DribbbleShot shot;
    private ShotCommentsAdapter adapter;
    private ShotDetailsPresenter presenter;

    public static ShotDetailsFragment with(DribbbleShot shot) {
        final Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_SHOT, shot);
        ShotDetailsFragment f = new ShotDetailsFragment();
        f.setArguments(args);
        return f;
    }

    public ShotDetailsFragment() {

    }

    @OnClick(R.id.img_dribble_player) void goToPlayerDetails() {
        final DribbbleUser player = shot.getUser();
        final Intent toPlayerDetails = PlayerDetailsActivity.launch(getActivity(), player);
        startActivity(toPlayerDetails);
    }

    @Override protected int layoutResource() {
        return R.layout.fragment_shot_details;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shot = (DribbbleShot) getArguments().getSerializable(ARGUMENT_SHOT);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        presenter = new ShotDetailsPresenter(this);
    }

    @Override public void onResume() {
        presenter.resume();
        presenter.fetchCommentsForShot(shot);
        super.onResume();
    }

    @Override public void onPause() {
        presenter.pause();
        super.onPause();
    }


    @Override public void fillDribbblePlayerInfo() {
        final DribbbleUser dribbbleUser = shot.getUser();
        userNameLabel.setText(dribbbleUser.getName());
        userLocationLabel.setText(" -   " + dribbbleUser.getLocation());
        loadUserImage(dribbbleUser);
    }

    @Override public void shotCommentsLoaded(List<ShotComment> comments) {

        if (comments != null && !comments.isEmpty()) {
            adapter.addComments(comments);
            return;
        }

        blankStateLabel.setText(R.string.blankstate_no_comments_for_shot);
    }

    @Override public void showLoadingIndicator() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideLoadingIndicator() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override public void showErrorMessage(ErrorMessageType type) {
        blankStateLabel.setVisibility(View.VISIBLE);

        switch (type) {
            case NETWORK_ERROR:
                blankStateLabel.setText(R.string.label_error_network);
                break;

            case REQUEST_ERROR:
                blankStateLabel.setText(R.string.label_error_request);
                break;
        }
    }


    @Override public void hideBlankstateMessage() {
        blankStateLabel.setVisibility(View.GONE);
    }

    private void setupRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new ShotCommentsAdapter(getActivity(), shot);
        commentsRecyclerView.setLayoutManager(manager);
        commentsRecyclerView.setAdapter(adapter);
    }

    private void loadUserImage(DribbbleUser dribbbleUser) {
        final String avatarUrl = dribbbleUser.getAvatarURL();
        if (!TextUtils.isEmpty(avatarUrl)) {

            Picasso.with(getActivity())
                    .load(avatarUrl)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.ic_user_placeholder)
                    .placeholder(R.drawable.ic_user_placeholder)
                    .transform(new CircularTransformation(avatarUrl))
                    .into(dribbleUserImage);
        }
    }

}
