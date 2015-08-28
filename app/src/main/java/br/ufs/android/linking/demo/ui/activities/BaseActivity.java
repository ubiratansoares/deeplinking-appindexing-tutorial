package br.ufs.android.linking.demo.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int layoutResource();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResource());
        ButterKnife.bind(this);
    }
}
