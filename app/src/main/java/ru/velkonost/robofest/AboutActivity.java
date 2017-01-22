package ru.velkonost.robofest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import adapters.AboutTabsFragmentAdapter;
import services.AlarmService;

import static managers.Initializations.changeActivityCompat;

public class AboutActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;


    // Слушатель выбора времени
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calendar = (Calendar) calNow.clone();

            calendar.set(Calendar.YEAR, 2016);
            calendar.set(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 22);
            calendar.set(Calendar.HOUR_OF_DAY, 22);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);



//            setAlarm(calendar);
        }
    };

    private PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("О мероприятии");
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initTabs();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Intent myIntent = new Intent(AboutActivity.this,
                AlarmService.class);

        mPendingIntent = PendingIntent.getService(AboutActivity.this, 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), mPendingIntent);

        Toast.makeText(AboutActivity.this, "Устанавливаем сигнализацию", Toast.LENGTH_LONG).show();

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
        viewPager = (ViewPager) findViewById(R.id.viewPagerAbout);

        AboutTabsFragmentAdapter adapter
                = new AboutTabsFragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    public void onClickPhoneNumber(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: 8-800-2222-108"));
        startActivity(intent);
    }


    public void onClickEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("mailto:"+ "robofestomsk@mail.ru"));
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

        if (id == R.id.main) {
            nextIntent = new Intent(AboutActivity.this, MainActivity.class);
        } else if (id == R.id.competition) {
            nextIntent = new Intent(AboutActivity.this, CompetitionActivity.class);
        } else if (id == R.id.translations) {
//            nextIntent = new Intent(MainActivity.this, TranslationsActivity.class);
        } else if (id == R.id.about) {
            nextIntent = new Intent(AboutActivity.this, AboutActivity.class);
        }

        final Intent finalNextIntent = nextIntent;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                changeActivityCompat(AboutActivity.this, finalNextIntent);
            }
        }, 350);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
