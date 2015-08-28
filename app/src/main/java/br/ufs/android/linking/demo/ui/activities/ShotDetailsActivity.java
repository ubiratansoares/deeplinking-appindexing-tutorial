package br.ufs.android.linking.demo.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import br.ufs.android.linking.demo.R;
import br.ufs.android.linking.demo.dto.DribbbleShot;
import br.ufs.android.linking.demo.ui.fragments.ShotDetailsFragment;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotDetailsActivity extends BaseActivity {

    private static final String EXTRA_SHOT = "extra.shot";

    public static Intent lauchIntent(Activity from, DribbbleShot shot) {
        final Intent i = new Intent(from, ShotDetailsActivity.class);
        i.putExtra(EXTRA_SHOT, shot);
        return i;
    }

    private DribbbleShot shot;

    @Override protected int layoutResource() {
        return R.layout.activity_fragment_container;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shot = (DribbbleShot) getIntent().getSerializableExtra(EXTRA_SHOT);

        setTitle(shot.getTitle());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ShotDetailsFragment.with(shot))
                .commit();
    }
}