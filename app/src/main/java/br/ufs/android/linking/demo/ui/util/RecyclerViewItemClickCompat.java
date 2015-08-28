package br.ufs.android.linking.demo.ui.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.ufs.android.linking.demo.R;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class RecyclerViewItemClickCompat {

    // Shameless stolen from Hugo Visser
    // http://www.littlerobots.nl/blog/Handle-Android-RecyclerView-Clicks/

    private final RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override public void onClick(View v) {
            if (onItemClickListener != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                onItemClickListener.onItemClicked(recyclerView, holder.getAdapterPosition(), v);
            }
        }
    };

    private View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override  public boolean onLongClick(View v) {
            if (onItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                return onItemLongClickListener.onItemLongClicked(recyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener attachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override public void onChildViewAttachedToWindow(View view) {
            if (onItemClickListener != null) {
                view.setOnClickListener(clickListener);
            }
            if (onItemLongClickListener != null) {
                view.setOnLongClickListener(longClickListener);
            }
        }

        @Override public void onChildViewDetachedFromWindow(View view) {

        }
    };

    private RecyclerViewItemClickCompat(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setTag(R.id.item_click_support, this);
        this.recyclerView.addOnChildAttachStateChangeListener(attachListener);
    }

    public static RecyclerViewItemClickCompat addTo(RecyclerView view) {
        RecyclerViewItemClickCompat support = (RecyclerViewItemClickCompat) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new RecyclerViewItemClickCompat(view);
        }
        return support;
    }

    public static RecyclerViewItemClickCompat removeFrom(RecyclerView view) {
        RecyclerViewItemClickCompat support = (RecyclerViewItemClickCompat) view.getTag(R.id.item_click_support);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public RecyclerViewItemClickCompat setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
        return this;
    }

    public RecyclerViewItemClickCompat setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(attachListener);
        view.setTag(R.id.item_click_support, null);
    }

    public interface OnItemClickListener {

        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {

        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}

