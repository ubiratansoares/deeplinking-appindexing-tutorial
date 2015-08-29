package br.ufs.android.linking.demo.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.ufs.android.linking.demo.R;
import br.ufs.android.linking.demo.dto.DribbbleUser;
import br.ufs.android.linking.demo.events.OnPopularShotLoaded;
import br.ufs.android.linking.demo.interactor.RESTInteractor;
import br.ufs.android.linking.demo.ui.adapters.PlayerShotsAdapter;
import butterknife.Bind;
import de.greenrobot.event.EventBus;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class PlayerDetailsActivity extends BaseActivity {

    private static final String EXTRA_PLAYER = "extra.player";

    @Bind(R.id.recyclerview_shots) RecyclerView shotsRecyclerView;

    private DribbbleUser player;
    private PlayerShotsAdapter adapter;

    public static Intent launch(Context from, DribbbleUser player) {
        Intent i = new Intent(from, PlayerDetailsActivity.class);
        i.putExtra(EXTRA_PLAYER, player);
        return i;
    }

    @Override protected int layoutResource() {
        return R.layout.activity_player_details;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = (DribbbleUser) getIntent().getSerializableExtra(EXTRA_PLAYER);
        setupRecyclerView();
        new RESTInteractor().fetchShotsForPlayer(String.valueOf(player.getId()));

    }

    @Override protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(OnPopularShotLoaded event) {
        adapter.add(event.getPopularShots());
    }

    private void setupRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new PlayerShotsAdapter(this, player);
        shotsRecyclerView.setLayoutManager(manager);
        shotsRecyclerView.setAdapter(adapter);
    }
}
