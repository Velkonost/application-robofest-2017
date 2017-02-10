package ru.velkonost.robofest;

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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import ru.velkonost.robofest.adapters.RecyclerView_Adapter;

import static ru.velkonost.robofest.managers.Initializations.changeActivityCompat;
import static ru.velkonost.robofest.managers.Initializations.hasConnection;

public class RecyclerView_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static RecyclerView recyclerView;

    //String and Integer array for Recycler View Items
    public static final ArrayList<String> TITLES = new ArrayList<String>(15);
    public static ArrayList arrayList = new ArrayList<String>(15);
    public static final Integer[] IMAGES = {R.drawable.background_about};
    public static int minCount = 0, maxCount = 15 ,page = 0, prevCount=-5;
    public static boolean checkImg = true;

    private ImageButton btnNextPage, btnPrevPage;

    private static String navigateFrom;//String to get Intent Value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_gallery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Галерея");
        setSupportActionBar(toolbar);

        btnNextPage = (ImageButton) findViewById(R.id.btn_nextp);
        btnPrevPage = (ImageButton) findViewById(R.id.btn_prevp);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        page = 0;
        initViews();
        populatRecyclerView();

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkImg) {
                    TITLES.clear();
                    arrayList.clear();
                  //  recyclerView.destroyDrawingCache();
                    page+=15;
                    populatRecyclerView();
                }
            }
        });

        btnPrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page>0) {
                    TITLES.clear();
                    arrayList.clear();
                    page -= 15;
                    populatRecyclerView();
                }

            }
        });
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

    // Initialize the view
    private void initViews() {
        recyclerView = (RecyclerView)
                findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));// Here 2 is no. of columns to be displayed
    }


    // populate the list view by adding data to arraylist
    private void populatRecyclerView() {
                for (int i = minCount; i < maxCount; i++) {
                    TITLES.add(i,"http://robofestomsk.ru/images/robofest2017_photo/bor" + (i+page) + ".jpg");
                    //TITLES.add("http://robofestomsk.ru/images/robofest2017_photo/thumb/bor_the"+i+".jpg");
                }
                for (int i = minCount; i < TITLES.size(); i++) {
                    arrayList.add(i, TITLES.get(i));
                }
                RecyclerView_Adapter adapter = new RecyclerView_Adapter(RecyclerView_Activity.this, arrayList);
                recyclerView.setAdapter(adapter);// set adapter on recyclerview
                //adapter.notifyDataSetChanged();// Notify the adapter
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent nextIntent = null;

        int id = item.getItemId();

        if (id == R.id.registration) {


            if (!hasConnection(RecyclerView_Activity.this)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RecyclerView_Activity.this);
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

            } else {

                nextIntent =
                        new Intent("ru.velkonost.Browser");
                nextIntent.putExtra("site", 1);
                nextIntent.setData(Uri.parse(
                        "https://docs.google.com/forms/d/e/1FAIpQLSfg7od0RMlO5CCML1MZB2dxVnS-3KG8rqTGZ2hitnVY2tdpxg/formResponse"
                ));
            }

        } else if (id == R.id.galery) {
            nextIntent = new Intent(RecyclerView_Activity.this, RecyclerView_Activity.class);
        } else if (id == R.id.about) {
            nextIntent = new Intent(RecyclerView_Activity.this, AboutActivity.class);
        } else if (id == R.id.organizers) {
            nextIntent = new Intent(RecyclerView_Activity.this, OrganizersActivity.class);
        } else if (id == R.id.contacts) {
            nextIntent = new Intent(RecyclerView_Activity.this, ContactsActivity.class);
        }


        final Intent finalNextIntent = nextIntent;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                changeActivityCompat(RecyclerView_Activity.this, finalNextIntent);
                finish();
            }
        }, 350);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}