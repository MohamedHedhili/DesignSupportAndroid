package com.mohamedhedhili.designsupportandroid;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.navigationView)
    NavigationView navigationView;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;
    @InjectView(R.id.tabs)
    TabLayout tabLayout;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setupWindowAnimations();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.setDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return RecyclerViewFragment.newInstance();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Tab " + position;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Explode explode = new Explode();
            getWindow().setExitTransition(explode);

            Fade fade = new Fade();
            getWindow().setReenterTransition(fade);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.fab)
    public void onFabClick() {
        Snackbar.make(fab, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }
}
