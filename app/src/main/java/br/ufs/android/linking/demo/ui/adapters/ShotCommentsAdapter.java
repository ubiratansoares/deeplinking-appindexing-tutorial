package br.ufs.android.linking.demo.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import br.ufs.android.linking.demo.dto.DribbbleUser;
import br.ufs.android.linking.demo.dto.ShotComment;
import br.ufs.android.linking.demo.ui.util.CircularTransformation;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotCommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_HEADER = 1;

    private Context context;
    private List<ShotComment> comments;
    private DribbbleShot shot;

    public ShotCommentsAdapter(Context context, DribbbleShot shot) {
        this.context = context;
        comments = new ArrayList<>();
        this.shot = shot;
    }

    @Override public int getItemViewType(int position) {

        if (position == 0) return VIEW_TYPE_HEADER;

        return VIEW_TYPE_NORMAL;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_HEADER) {
            final View headerView = inflater.inflate(R.layout.list_header_shot_details, parent, false);
            return new HeaderHolder(headerView);
        }

        if (viewType == VIEW_TYPE_NORMAL) {
            final View v = inflater.inflate(R.layout.list_item_shot_comment, parent, false);
            return new CommentHolder(v);
        }

        throw new IllegalStateException("Unrecognized ViewType for this adapter");
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderHolder) {
            fillHeader((HeaderHolder) holder);
            return;
        }

        if (holder instanceof CommentHolder) {
            fillComment((CommentHolder) holder, position);
        }
    }


    @Override public int getItemCount() {
        return comments.size() + 1;
    }

    public void addComments(List<ShotComment> newComments) {
        comments.addAll(newComments);
        notifyDataSetChanged();
    }

    private void fillHeader(HeaderHolder holder) {

        if (!isEmpty(shot.getDescription())) {
            holder.shotDescriptionLabel.setText(Html.fromHtml(shot.getDescription()));
        } else {
            holder.shotDescriptionLabel.setText("No description avaliable");
        }

        Picasso.with(context)
                .load(shot.getShotURL())
                .fit()
                .centerCrop()
                .error(R.drawable.img_placeholder)
                .placeholder(R.drawable.img_placeholder)
                .into(holder.shotImage);
    }

    private void fillComment(CommentHolder holder, int position) {
        final ShotComment comment = comments.get(position - 1);
        holder.commentLabel.setText(comment.getComment());

        final DribbbleUser commenter = comment.getCommenter();

        if (!isEmpty(commenter.getName())) {
            holder.playerNameLabel.setText(commenter.getName());
        } else {
            holder.playerNameLabel.setText("Anounymous User");
        }

        if (!isEmpty(commenter.getAvatarURL())) {

            Picasso.with(context)
                    .load(commenter.getAvatarURL())
                    .fit()
                    .tag(holder)
                    .centerCrop()
                    .error(R.drawable.img_placeholder)
                    .transform(new CircularTransformation(commenter.getAvatarURL()))
                    .into(holder.playerAvatarImage);
        }
    }

    static class CommentHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_player_name) TextView playerNameLabel;
        @Bind(R.id.img_dribble_player) ImageView playerAvatarImage;
        @Bind(R.id.txt_comment) TextView commentLabel;

        public CommentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class HeaderHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_shot) ImageView shotImage;
        @Bind(R.id.txt_shot_description) TextView shotDescriptionLabel;

        public HeaderHolder(View headerView) {
            super(headerView);
            ButterKnife.bind(this, headerView);
        }
    }
}