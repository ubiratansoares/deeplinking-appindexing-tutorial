package br.ufs.android.linking.demo.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract int layoutResource();

    @Override public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(layoutResource(), container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}