package br.ufs.android.linking.demo.ui.activities;

import android.os.Bundle;

import br.ufs.android.linking.demo.R;
import br.ufs.android.linking.demo.ui.fragments.PopularShotsFragment;

public class MainActivity extends BaseActivity {

    @Override protected int layoutResource() {
        return R.layout.activity_fragment_container;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new PopularShotsFragment())
                .commit();
    }
}
