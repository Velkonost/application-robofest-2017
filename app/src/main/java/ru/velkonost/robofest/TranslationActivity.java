package ru.velkonost.robofest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import adapters.TranslationTabsFragmentAdapter;

import static managers.Initializations.changeActivityCompat;

/**
 * Created by Andrey on 27.01.2017.
 */
public class TranslationActivity  extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Трансляции");
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initTabs();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPagerTranslation);

        TranslationTabsFragmentAdapter adapter
                = new TranslationTabsFragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    public void openGoogleForms(View view) {

        Intent intent =
                new Intent("ru.velkonost.Browser");
        intent.setData(Uri.parse(
                "https://docs.google.com/forms/d/e/1FAIpQLSfg7od0RMlO5CCML1MZB2dxVnS-3KG8rqTGZ2hitnVY2tdpxg/formResponse"
        ));
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent nextIntent = null;

        int id = item.getItemId();

//        if (id == R.id.main) {
//            nextIntent = new Intent(TranslationActivity.this, MainActivity.class);
//        } else if (id == R.id.competition) {
//            nextIntent = new Intent(TranslationActivity.this, CompetitionActivity.class);
//        } else if (id == R.id.translations) {
//            nextIntent = new Intent(TranslationActivity.this, TranslationActivity.class);
//        } else if (id == R.id.about) {
//            nextIntent = new Intent(TranslationActivity.this, AboutActivity.class);
//        }

        final Intent finalNextIntent = nextIntent;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                changeActivityCompat(TranslationActivity.this, finalNextIntent);
            }
        }, 350);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

