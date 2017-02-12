package ru.velkonost.robofest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ru.velkonost.robofest.adapters.MainTabsFragmentAdapter;

import static ru.velkonost.robofest.managers.Initializations.changeActivityCompat;
import static ru.velkonost.robofest.managers.Initializations.hasConnection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnDay1;
    private Button btnDay2;

    private ImageView imageMap, imageGraphic, imageDay;
    private int day = 1;

    private LinearLayout dayImage;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        if (!hasConnection(MainActivity.this)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Ошибка")
                    .setMessage("Отсутствует интернет-соединение!")
                    .setCancelable(false)
                    .setNegativeButton("Хорошо",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

        }

        initTabs();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    public void openMap (View view) {
        Intent intent = new Intent(MainActivity.this, FullScreenPhotoActivity.class);
        intent.putExtra("Photo", 1);
        MainActivity.this.startActivity(intent);
    }

    public void openGraphic (View view) {
        Intent intent = new Intent(MainActivity.this, FullScreenPhotoActivity.class);
        intent.putExtra("Photo", 2);
        MainActivity.this.startActivity(intent);
    }

    public void openMain (View view) {

        if (!hasConnection(MainActivity.this)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Ошибка")
                    .setMessage("Отсутствует интернет-соединение!")
                    .setCancelable(false)
                    .setNegativeButton("Хорошо",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

            return;
        }

        final Intent finalNextIntent = new Intent("ru.velkonost.Browser");
        finalNextIntent.putExtra("site", 2);
        finalNextIntent.setData(Uri.parse(
                "https://www.robofestomsk.ru/index.html"
        ));
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

    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);

        MainTabsFragmentAdapter adapter
                = new MainTabsFragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    public void registrationOpen (View view) {

        if (!hasConnection(MainActivity.this)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Ошибка")
                    .setMessage("Отсутствует интернет-соединение!")
                    .setCancelable(false)
                    .setNegativeButton("Хорошо",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

            return;
        }

        Intent nextIntent;
        nextIntent =
                new Intent("ru.velkonost.Browser");
        nextIntent.putExtra("site", 1);
        nextIntent.setData(Uri.parse(
                "https://docs.google.com/forms/d/e/1FAIpQLSfg7od0RMlO5CCML1MZB2dxVnS-3KG8rqTGZ2hitnVY2tdpxg/formResponse"
        ));

        changeActivityCompat(MainActivity.this, nextIntent);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent nextIntent = null;

        int id = item.getItemId();

        if (id == R.id.registration) {

            if (!hasConnection(MainActivity.this)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ошибка")
                        .setMessage("Отсутствует интернет-соединение!")
                        .setCancelable(false)
                        .setNegativeButton("Хорошо",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();

                nextIntent = new Intent(MainActivity.this, MainActivity.class);
            } else {

                nextIntent =
                        new Intent("ru.velkonost.Browser");
                nextIntent.putExtra("site", 1);
                nextIntent.setData(Uri.parse(
                        "https://docs.google.com/forms/d/e/1FAIpQLSfg7od0RMlO5CCML1MZB2dxVnS-3KG8rqTGZ2hitnVY2tdpxg/formResponse"
                ));
            }

        } else if (id == R.id.galery) {
            nextIntent = new Intent(MainActivity.this, RecyclerView_Activity.class);
        } else if (id == R.id.about) {
            nextIntent = new Intent(MainActivity.this, AboutActivity.class);
        } else if (id == R.id.organizers) {
            nextIntent = new Intent(MainActivity.this, OrganizersActivity.class);
        } else if (id == R.id.contacts) {
            nextIntent = new Intent(MainActivity.this, ContactsActivity.class);
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
