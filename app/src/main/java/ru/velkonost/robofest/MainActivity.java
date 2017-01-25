package ru.velkonost.robofest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static managers.Initializations.changeActivityCompat;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnDay1;
    private Button btnDay2;

    private RelativeLayout rlDayDesc;

    private TextView firstTime, firstMeasure;
    private TextView secondTime, secondMeasure;
    private TextView thirdTime, thirdMeasure;
    private TextView forthTime, forthMeasure;
    private TextView fifthTime, fifthMeasure;

    private ImageView imageMap;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Главная");
        setSupportActionBar(toolbar);

        btnDay1 = (Button) findViewById(R.id.buttonDay1);
        btnDay2 = (Button) findViewById(R.id.buttonDay2);

        rlDayDesc = (RelativeLayout) findViewById(R.id.rlDayDesc);

        firstTime = (TextView) findViewById(R.id.firstTime);
        secondTime = (TextView) findViewById(R.id.secondTime);
        thirdTime = (TextView) findViewById(R.id.thirdTime);
        forthTime = (TextView) findViewById(R.id.forthTime);
        fifthTime = (TextView) findViewById(R.id.fifthTime);

        firstMeasure = (TextView) findViewById(R.id.firstMeasure);
        secondMeasure = (TextView) findViewById(R.id.secondMeasure);
        thirdMeasure = (TextView) findViewById(R.id.thirdMeasure);
        forthMeasure = (TextView) findViewById(R.id.forthMeasure);
        fifthMeasure = (TextView) findViewById(R.id.fifthMeasure);

        imageMap = (ImageView) findViewById(R.id.imageMap);

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

        btnDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlDayDesc.setVisibility(View.VISIBLE);

                firstTime.setText(getResources().getString(R.string.day_one_time_first) + "     ");
                secondTime.setText(getResources().getString(R.string.day_one_time_second) + "     ");
                thirdTime.setText(getResources().getString(R.string.day_one_time_third) + "     ");
                forthTime.setText(getResources().getString(R.string.day_one_time_forth) + "     ");
                fifthTime.setText(getResources().getString(R.string.day_one_time_fifth) + "     ");

                firstMeasure.setText(getResources().getString(R.string.day_one_desc_first));
                secondMeasure.setText(getResources().getString(R.string.day_one_desc_second));
                thirdMeasure.setText(getResources().getString(R.string.day_one_desc_third));
                forthMeasure.setText(getResources().getString(R.string.day_one_desc_forth));
                fifthMeasure.setText(getResources().getString(R.string.day_one_desc_fifth));



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
                rlDayDesc.setVisibility(View.VISIBLE);

                firstTime.setText(getResources().getString(R.string.day_two_time_first) + "     ");
                secondTime.setText(getResources().getString(R.string.day_two_time_second) + "     ");
                thirdTime.setText(getResources().getString(R.string.day_two_time_third) + "     ");
                forthTime.setText(getResources().getString(R.string.day_two_time_forth) + "     ");
                fifthTime.setText(getResources().getString(R.string.day_two_time_fifth) + "     ");

                firstMeasure.setText(getResources().getString(R.string.day_two_desc_first));
                secondMeasure.setText(getResources().getString(R.string.day_two_desc_second));
                thirdMeasure.setText(getResources().getString(R.string.day_two_desc_third));
                forthMeasure.setText(getResources().getString(R.string.day_two_desc_forth));
                fifthMeasure.setText(getResources().getString(R.string.day_two_desc_fifth));


                btnDay1.setBackground(ContextCompat.getDrawable(MainActivity.this,
                        R.drawable.main_activity_button_left));
                btnDay2.setBackground(ContextCompat.getDrawable(MainActivity.this,
                        R.drawable.main_activity_button_right_pressed));

                day = 2;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void showSubjectAreas(View view) {
        final View popupView;
        final PopupWindow popupWindow;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        popupView = layoutInflater.inflate(R.layout.popup_subject_areas, null);

        popupWindow = new PopupWindow(popupView,
                WRAP_CONTENT, height - dp2px(120));


//        recyclerView = (RecyclerView) popupView
//                .findViewById(R.id.recyclerViewBoardInvite);


        Button btn1, btn2, btn3, btn4, btn5;
        TextView areaName;
        TextView fisrtTimePopup, secondTimePopup, thirdTimePopup, forthTimePopup, fifthTimePopup,
                sixthTimePopup, seventhTimePopup;

        btn1 = (Button) popupView.findViewById(R.id.btn1Popup);
        btn2 = (Button) popupView.findViewById(R.id.btn2Popup);
        btn3 = (Button) popupView.findViewById(R.id.btn3Popup);
        btn4 = (Button) popupView.findViewById(R.id.btn4Popup);
        btn5 = (Button) popupView.findViewById(R.id.btn5Popup);

        areaName = (TextView) popupView.findViewById(R.id.area_name);

//        fisrtTimePopup = (TextView) popupView.findViewById(R.id.firstTimePopup);
//        secondTimePopup = (TextView) popupView.findViewById(R.id)

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });




        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(android.R.color.transparent)));
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                MainActivity.this.getResources().getDisplayMetrics());
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
            nextIntent = new Intent(MainActivity.this, MainActivity.class);
        } else if (id == R.id.competition) {
            nextIntent = new Intent(MainActivity.this, CompetitionActivity.class);
        } else if (id == R.id.translations) {
//            nextIntent = new Intent(MainActivity.this, TranslationsActivity.class);
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
