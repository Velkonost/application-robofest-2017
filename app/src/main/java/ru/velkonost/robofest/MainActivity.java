package ru.velkonost.robofest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import static managers.Initializations.changeActivityCompat;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnDay1;
    private Button btnDay2;

    private ImageView imageMap, imageGraphic, imageDay;
    private int day;

    private LinearLayout dayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Главная");
        setSupportActionBar(toolbar);

        btnDay1 = (Button) findViewById(R.id.buttonDay1);
        btnDay2 = (Button) findViewById(R.id.buttonDay2);

        dayImage = (LinearLayout) findViewById(R.id.dayImage);

        imageMap = (ImageView) findViewById(R.id.imageMap);
        imageGraphic = (ImageView) findViewById(R.id.imageGraphic);
        imageDay = (ImageView) findViewById(R.id.imageDay);

        Display display = getWindowManager().getDefaultDisplay();
        Point sizeP = new Point();
        display.getSize(sizeP);
        int width = sizeP.x;
        int height = sizeP.y;

        Glide.with(MainActivity.this)
                .load("http://www.robofestomsk.ru/images/robofestomsk_sheme.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .override(width, 200)
                .into(imageMap);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);


        imageMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FullScreenPhotoActivity.class);
                intent.putExtra("Photo", 1);
                MainActivity.this.startActivity(intent);
            }
        });

        imageGraphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FullScreenPhotoActivity.class);
                intent.putExtra("Photo", 2);
                MainActivity.this.startActivity(intent);
            }
        });

        imageDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FullScreenPhotoActivity.class);
                intent.putExtra("Photo", day == 1 ? 3 : 4);
                MainActivity.this.startActivity(intent);

            }
        });

        btnDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dayImage.setVisibility(View.VISIBLE);
                imageDay.setImageDrawable(getResources().getDrawable(R.drawable.day1));



                btnDay1.setBackground(ContextCompat.getDrawable(MainActivity.this,
                        R.drawable.main_activity_button_left_pressed));
                btnDay2.setBackground(ContextCompat.getDrawable(MainActivity.this,
                        R.drawable.main_activity_button_right));

                day = 1;

            }
        });

        btnDay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dayImage.setVisibility(View.VISIBLE);

                imageDay.setImageDrawable(getResources().getDrawable(R.drawable.day2));

                btnDay1.setBackground(ContextCompat.getDrawable(MainActivity.this,
                        R.drawable.main_activity_button_left));
                btnDay2.setBackground(ContextCompat.getDrawable(MainActivity.this,
                        R.drawable.main_activity_button_right_pressed));

                day = 2;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent nextIntent = null;

        int id = item.getItemId();

        if (id == R.id.main) {
            nextIntent = new Intent(MainActivity.this, MainActivity.class);
        } else if (id == R.id.competition) {
            nextIntent = new Intent(MainActivity.this, CompetitionActivity.class);
        } else if (id == R.id.translations) {
            nextIntent = new Intent(MainActivity.this, TranslationActivity.class);
        } else if (id == R.id.about) {
            nextIntent = new Intent(MainActivity.this, AboutActivity.class);
        }

        final Intent finalNextIntent = nextIntent;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /**
                 * Обновляет страницу.
                 * {@link Initializations#changeActivityCompat(Activity, Intent)}
                 * */
                changeActivityCompat(MainActivity.this, finalNextIntent);
            }
        }, 350);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
