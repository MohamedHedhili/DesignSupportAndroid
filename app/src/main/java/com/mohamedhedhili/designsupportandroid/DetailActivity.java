package com.mohamedhedhili.designsupportandroid;


import android.annotation.TargetApi;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetailActivity extends AppCompatActivity {

    public static final String MY_OBJECT = "MY_OBJECT";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @InjectView(R.id.backgroundImageView)
    ImageView backgroundImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.inject(this);

        setupWindowAnimations();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MyObject myObject = (MyObject) getIntent().getSerializableExtra(MY_OBJECT);
        collapsingToolbar.setTitle(myObject.getText());

        supportPostponeEnterTransition();

        Picasso.with(this).load(myObject.getImageUrl()).centerCrop().fit().into(backgroundImageView, new Callback() {
            @Override
            public void onSuccess() {
                supportStartPostponedEnterTransition();
            }

            @Override
            public void onError() {

            }
        });
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            getWindow().setReturnTransition(fade);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
