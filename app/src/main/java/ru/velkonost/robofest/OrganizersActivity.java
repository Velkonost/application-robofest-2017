package ru.velkonost.robofest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import static ru.velkonost.robofest.managers.Initializations.changeActivityCompat;
import static ru.velkonost.robofest.managers.Initializations.hasConnection;

public class OrganizersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizers);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Организаторы");
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void openMain (View view) {

        if (!hasConnection(OrganizersActivity.this)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(OrganizersActivity.this);
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
                changeActivityCompat(OrganizersActivity.this, finalNextIntent);
            }
        }, 350);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent nextIntent = null;

        int id = item.getItemId();

        if (id == R.id.registration) {

            if (!hasConnection(OrganizersActivity.this)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(OrganizersActivity.this);
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

                nextIntent = new Intent(OrganizersActivity.this, OrganizersActivity.class);
            } else {

                nextIntent =
                        new Intent("ru.velkonost.Browser");
                nextIntent.putExtra("site", 1);
                nextIntent.setData(Uri.parse(
                        "https://docs.google.com/forms/d/e/1FAIpQLSfg7od0RMlO5CCML1MZB2dxVnS-3KG8rqTGZ2hitnVY2tdpxg/formResponse"
                ));
            }

        } else if (id == R.id.galery) {
            nextIntent = new Intent(OrganizersActivity.this, RecyclerView_Activity.class);
        } else if (id == R.id.about) {
            nextIntent = new Intent(OrganizersActivity.this, AboutActivity.class);
        } else if (id == R.id.organizers) {
            nextIntent = new Intent(OrganizersActivity.this, OrganizersActivity.class);
        } else if (id == R.id.contacts) {
            nextIntent = new Intent(OrganizersActivity.this, ContactsActivity.class);
        }


        final Intent finalNextIntent = nextIntent;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                changeActivityCompat(OrganizersActivity.this, finalNextIntent);
                finish();
            }
        }, 350);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
