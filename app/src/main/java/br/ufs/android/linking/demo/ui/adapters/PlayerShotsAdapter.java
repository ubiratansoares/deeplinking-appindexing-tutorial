package br.ufs.android.linking.demo.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.ufs.android.linking.demo.R;
import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.dto.DribbbleUser;
import br.ufs.android.linking.demo.ui.util.CircularTransformation;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class PlayerShotsAdapter extends HeaderAdapter {

    private final Context context;
    private List<DribbbleShot> shots;
    private final DribbbleUser player;

    public PlayerShotsAdapter(Context context, DribbbleUser player) {
        super(context);
        this.context = context;
        this.player = player;
        shots = new ArrayList<>();
    }

    @Override protected int getHeaderLayoutResource() {
        return R.layout.list_header_player_info;
    }

    @Override protected int getItemLayoutResource() {
        return R.layout.list_item_shot;
    }

    @Override public int getItemCount() {
        return shots.size();
    }

    @Override protected void onBindHeader(RecyclerView.ViewHolder holder) {

        final HeaderHolder headerHolder = (HeaderHolder) holder;

        headerHolder.userNameLabel.setText(player.getName());
        headerHolder.userLocationLabel.setText(player.getLocation());
        headerHolder.likesCountLabel.setText(String.valueOf(player.getLikesCount()));
        headerHolder.followerCountLabel.setText(String.valueOf(player.getFollowersCount()));
        headerHolder.shotsCountLabel.setText(String.valueOf(player.getShotsCount()));

        String avatarURL = player.getAvatarURL();

        Picasso.with(context).load(avatarURL)
                .fit()
                .centerCrop()
                .error(R.drawable.ic_user_placeholder)
                .placeholder(R.drawable.ic_user_placeholder)
                .transform(new CircularTransformation(avatarURL))
                .into(headerHolder.dribbleUserImage);
    }

    @Override protected void onBindItem(RecyclerView.ViewHolder holder, int position) {
        DribbbleShot shot = shots.get(position);
        ShotHolder shotHolder = (ShotHolder) holder;
        shotHolder.shotNameLabel.setText(shot.getTitle());
        shotHolder.shotViewsLabel.setText(String.valueOf(shot.getViews()));

        Picasso.with(context).load(shot.getShotURL())
                .fit()
                .centerCrop()
                .error(R.drawable.img_placeholder)
                .placeholder(R.drawable.img_placeholder)
                .into(shotHolder.shotImage);
    }

    @Override protected RecyclerView.ViewHolder getItemHolder(View itemView) {
        return new ShotHolder(itemView);
    }

    @Override protected RecyclerView.ViewHolder getHeaderHolder(View headerView) {
        return new HeaderHolder(headerView);
    }

    public void add(List<DribbbleShot> extraShots) {
        shots.addAll(extraShots);
        notifyDataSetChanged();
    }

    static class ShotHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_shot_name) TextView shotNameLabel;
        @Bind(R.id.txt_shot_views) TextView shotViewsLabel;
        @Bind(R.id.img_popular_shot) ImageView shotImage;

        public ShotHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_player_name) TextView userNameLabel;
        @Bind(R.id.txt_location) TextView userLocationLabel;
        @Bind(R.id.img_dribble_player) ImageView dribbleUserImage;
        @Bind(R.id.txt_shots_count) TextView shotsCountLabel;
        @Bind(R.id.txt_followers_count) TextView followerCountLabel;
        @Bind(R.id.txt_likes_count) TextView likesCountLabel;

        public HeaderHolder(View headerView) {
            super(headerView);
            ButterKnife.bind(this, headerView);
        }
    }
}
