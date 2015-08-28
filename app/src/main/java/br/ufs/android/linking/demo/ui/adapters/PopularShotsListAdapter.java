package br.ufs.android.linking.demo.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.ufs.android.linking.demo.R;
import br.ufs.android.linking.demo.dto.DribbbleShot;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class PopularShotsListAdapter extends RecyclerView.Adapter<PopularShotsListAdapter.ShotHolder> {

    private List<DribbbleShot> shots;
    private Context context;

    public PopularShotsListAdapter(Context context) {
        this.shots = new ArrayList<>();
        this.context = context;
    }

    @Override public ShotHolder onCreateViewHolder(ViewGroup parent, int position) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(R.layout.list_item_shot, parent, false);
        return new ShotHolder(itemView);
    }

    @Override public void onBindViewHolder(ShotHolder shotHolder, int position) {
        DribbbleShot shot = shots.get(position);
        shotHolder.shotNameLabel.setText(shot.getTitle());
        shotHolder.shotViewsLabel.setText(String.valueOf(shot.getViews()));

        Picasso.with(context).load(shot.getShotURL())
                .fit()
                .centerCrop()
                .error(R.drawable.img_placeholder)
                .placeholder(R.drawable.img_placeholder)
                .into(shotHolder.shotImage);
    }

    @Override public int getItemCount() {
        return shots.size();
    }

    public void add(List<DribbbleShot> extraShots) {
        shots.addAll(extraShots);
        notifyDataSetChanged();
    }

    public DribbbleShot getItem(int position) {
        return shots.get(position);
    }

    static class ShotHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.txt_shot_name) TextView shotNameLabel;
        @Bind(R.id.txt_shot_views) TextView shotViewsLabel;
        @Bind(R.id.img_popular_shot) ImageView shotImage;

        public ShotHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override public void onClick(View v) {
            final int position = getAdapterPosition();
        }
    }
}
