package br.ufs.android.linking.demo.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public abstract class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_HEADER = 1;

    private LayoutInflater inflater;

    public HeaderAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    protected abstract int getHeaderLayoutResource();

    protected abstract int getItemLayoutResource();

    protected abstract void onBindItem(RecyclerView.ViewHolder holder, int position);

    protected abstract void onBindHeader(RecyclerView.ViewHolder holder);

    protected abstract RecyclerView.ViewHolder getItemHolder(View itemView);

    protected abstract RecyclerView.ViewHolder getHeaderHolder(View headerView);


    @Override public int getItemViewType(int position) {

        if (position == 0) return VIEW_TYPE_HEADER;

        return VIEW_TYPE_NORMAL;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_HEADER) {
            final View headerView = inflater.inflate(getHeaderLayoutResource(), parent, false);
            return getHeaderHolder(headerView);
        }

        if (viewType == VIEW_TYPE_NORMAL) {
            final View itemView = inflater.inflate(getItemLayoutResource(), parent, false);
            return getItemHolder(itemView);
        }

        throw new IllegalStateException("Unrecognized ViewType for this adapter");
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {
            onBindHeader(holder);
            return;
        }

        onBindItem(holder, position - 1);

    }


}
