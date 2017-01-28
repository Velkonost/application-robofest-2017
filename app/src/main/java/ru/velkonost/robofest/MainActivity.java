package ru.velkonost.robofest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
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


        Button btn1, btn2, btn3, btn4, btn5;

        final TextView areaName;
        final TextView firstTimePopup, secondTimePopup, thirdTimePopup, forthTimePopup, fifthTimePopup,
                sixthTimePopup, seventhTimePopup;

        final TextView firstHeaderPopup, secondHeaderPopup, thirdHeaderPopup, forthHeaderPopup,
                fifthHeaderPopup, sixthHeaderPopup, seventhHeaderPopup;

        final TextView firstMeasurePopup, secondMeasurePopup, thirdMeasurePopup, forthMeasurePopup,
                fifthMeasurePopup, sixthMeasurePopup, seventhMeasurePopup;

        final TextView firstAudiencePopup, secondAudiencePopup, thirdAudiencePopup, forthAudiencePopup,
                fifthAudiencePopup, sixthAudiencePopup, seventhAudiencePopup;

        final TextView firstFooterPopup, secondFooterPopup, thirdFooterPopup, forthFooterPopup,
                fifthFooterPopup, sixthFooterPopup, seventhFooterPopup;

        btn1 = (Button) popupView.findViewById(R.id.btn1Popup);
        btn2 = (Button) popupView.findViewById(R.id.btn2Popup);
        btn3 = (Button) popupView.findViewById(R.id.btn3Popup);
        btn4 = (Button) popupView.findViewById(R.id.btn4Popup);
        btn5 = (Button) popupView.findViewById(R.id.btn5Popup);

        areaName = (TextView) popupView.findViewById(R.id.area_name);

        firstTimePopup = (TextView) popupView.findViewById(R.id.firstTimePopup);
        secondTimePopup = (TextView) popupView.findViewById(R.id.secondTimePopup);
        thirdTimePopup = (TextView) popupView.findViewById(R.id.thirdTimePopup);
        forthTimePopup = (TextView) popupView.findViewById(R.id.forthTimePopup);
        fifthTimePopup = (TextView) popupView.findViewById(R.id.fifthTimePopup);
        sixthTimePopup = (TextView) popupView.findViewById(R.id.sixthTimePopup);
        seventhTimePopup = (TextView) popupView.findViewById(R.id.seventhTimePopup);

        firstHeaderPopup = (TextView) popupView.findViewById(R.id.firstHeaderPopup);
        secondHeaderPopup = (TextView) popupView.findViewById(R.id.secondHeaderPopup);
        thirdHeaderPopup = (TextView) popupView.findViewById(R.id.thirdHeaderPopup);
        forthHeaderPopup = (TextView) popupView.findViewById(R.id.forthHeaderPopup);
        fifthHeaderPopup = (TextView) popupView.findViewById(R.id.fifthHeaderPopup);
        sixthHeaderPopup = (TextView) popupView.findViewById(R.id.sixthHeaderPopup);
        seventhHeaderPopup = (TextView) popupView.findViewById(R.id.seventhHeaderPopup);

        firstMeasurePopup = (TextView) popupView.findViewById(R.id.firstMeasurePopup);
        secondMeasurePopup = (TextView) popupView.findViewById(R.id.secondMeasurePopup);
        thirdMeasurePopup = (TextView) popupView.findViewById(R.id.thirdMeasurePopup);
        forthMeasurePopup = (TextView) popupView.findViewById(R.id.forthMeasurePopup);
        fifthMeasurePopup = (TextView) popupView.findViewById(R.id.fifthMeasurePopup);
        sixthMeasurePopup = (TextView) popupView.findViewById(R.id.sixthMeasurePopup);
        seventhMeasurePopup = (TextView) popupView.findViewById(R.id.seventhMeasurePopup);

        firstAudiencePopup = (TextView) popupView.findViewById(R.id.firstAudiencePopup);
        secondAudiencePopup = (TextView) popupView.findViewById(R.id.secondAudiencePopup);
        thirdAudiencePopup = (TextView) popupView.findViewById(R.id.thirdAudiencePopup);
        forthAudiencePopup = (TextView) popupView.findViewById(R.id.forthAudiencePopup);
        fifthAudiencePopup = (TextView) popupView.findViewById(R.id.fifthAudiencePopup);
        sixthAudiencePopup = (TextView) popupView.findViewById(R.id.sixthAudiencePopup);
        seventhAudiencePopup = (TextView) popupView.findViewById(R.id.seventhAudiencePopup);

        firstFooterPopup = (TextView) popupView.findViewById(R.id.firstFooterPopup);
        secondFooterPopup = (TextView) popupView.findViewById(R.id.secondFooterPopup);
        thirdFooterPopup = (TextView) popupView.findViewById(R.id.thirdFooterPopup);
        forthFooterPopup = (TextView) popupView.findViewById(R.id.forthFooterPopup);
        fifthFooterPopup = (TextView) popupView.findViewById(R.id.fifthFooterPopup);
        sixthFooterPopup = (TextView) popupView.findViewById(R.id.sixthFooterPopup);
        seventhFooterPopup = (TextView) popupView.findViewById(R.id.seventhFooterPopup);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaName.setText(R.string.area_name_1);

                if (day == 1) {

                    firstTimePopup.setText(getResources().getString(R.string.day1_btn1_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day1_btn1_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day1_btn1_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day1_btn1_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day1_btn1_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day1_btn1_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setText(getResources().getString(R.string.day1_btn1_seventh) + "     ");
                    seventhTimePopup.setVisibility(View.VISIBLE);
                    seventhTimePopup.setTextSize(14);


                    firstHeaderPopup.setText(R.string.day1_btn1_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day1_btn1_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day1_btn1_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day1_btn1_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day1_btn1_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day1_btn1_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setText(R.string.day1_btn1_seventh_header);
                    seventhHeaderPopup.setVisibility(View.VISIBLE);
                    seventhHeaderPopup.setTextSize(16);


                    firstMeasurePopup.setVisibility(View.INVISIBLE);
                    firstMeasurePopup.setTextSize(0);

                    secondMeasurePopup.setText(R.string.day1_btn1_second_desc);
                    thirdMeasurePopup.setText(R.string.day1_btn1_third_desc);
                    forthMeasurePopup.setText(R.string.day1_btn1_forth_desc);
                    fifthMeasurePopup.setText(R.string.day1_btn1_fifth_desc);
                    sixthMeasurePopup.setText(R.string.day1_btn1_sixth_desc);
                    seventhMeasurePopup.setText(R.string.day1_btn1_seventh_desc);

                    firstAudiencePopup.setVisibility(View.INVISIBLE);
                    firstAudiencePopup.setTextSize(0);

                    secondAudiencePopup.setText(R.string.day1_btn1_second_audience);
                    thirdAudiencePopup.setText(R.string.day1_btn1_third_audience);
                    forthAudiencePopup.setText(R.string.day1_btn1_forth_audience);
                    fifthAudiencePopup.setText(R.string.day1_btn1_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day1_btn1_sixth_audience);
                    seventhAudiencePopup.setText(R.string.day1_btn1_seventh_audience);

                    firstFooterPopup.setVisibility(View.INVISIBLE);
                    firstFooterPopup.setTextSize(0);

                    secondFooterPopup.setText(R.string.day1_btn1_second_footer);
                    thirdFooterPopup.setText(R.string.day1_btn1_third_footer);
                    forthFooterPopup.setText(R.string.day1_btn1_forth_footer);
                    fifthFooterPopup.setText(R.string.day1_btn1_fifth_footer);
                    sixthFooterPopup.setText(R.string.day1_btn1_sixth_footer);
                    seventhFooterPopup.setText(R.string.day1_btn1_seventh_footer);


                } else {

                    firstTimePopup.setText(getResources().getString(R.string.day2_btn1_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day2_btn1_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day2_btn1_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day2_btn1_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day2_btn1_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day2_btn1_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setText(getResources().getString(R.string.day2_btn1_seventh) + "     ");
                    seventhTimePopup.setVisibility(View.VISIBLE);
                    seventhTimePopup.setTextSize(14);


                    firstHeaderPopup.setText(R.string.day2_btn1_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day2_btn1_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day2_btn1_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day2_btn1_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day2_btn1_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day2_btn1_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setText(R.string.day2_btn1_seventh_header);
                    seventhHeaderPopup.setVisibility(View.VISIBLE);
                    seventhHeaderPopup.setTextSize(16);

                    firstMeasurePopup.setText(R.string.day2_btn1_first_desc);
                    secondMeasurePopup.setText(R.string.day2_btn1_second_desc);
                    thirdMeasurePopup.setText(R.string.day2_btn1_third_desc);
                    forthMeasurePopup.setText(R.string.day2_btn1_forth_desc);
                    fifthMeasurePopup.setText(R.string.day2_btn1_fifth_desc);
                    sixthMeasurePopup.setText(R.string.day2_btn1_sixth_desc);
                    seventhMeasurePopup.setText(R.string.day2_btn1_seventh_desc);
                    seventhTimePopup.setVisibility(View.VISIBLE);

                    firstAudiencePopup.setText(R.string.day2_btn1_first_audience);
                    secondAudiencePopup.setText(R.string.day2_btn1_second_audience);
                    thirdAudiencePopup.setText(R.string.day2_btn1_third_audience);
                    forthAudiencePopup.setText(R.string.day2_btn1_forth_audience);
                    fifthAudiencePopup.setText(R.string.day2_btn1_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day2_btn1_sixth_audience);
                    seventhAudiencePopup.setText(R.string.day2_btn1_seventh_audience);
                    seventhTimePopup.setVisibility(View.VISIBLE);

                    firstFooterPopup.setText(R.string.day2_btn1_first_footer);
                    secondFooterPopup.setText(R.string.day2_btn1_second_footer);
                    thirdFooterPopup.setText(R.string.day2_btn1_third_footer);
                    forthFooterPopup.setText(R.string.day2_btn1_forth_footer);
                    fifthFooterPopup.setText(R.string.day2_btn1_fifth_footer);
                    sixthFooterPopup.setText(R.string.day2_btn1_sixth_footer);
                    seventhFooterPopup.setText(R.string.day2_btn1_seventh_footer);
                    seventhTimePopup.setVisibility(View.VISIBLE);

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaName.setText(R.string.area_name_2);

                if (day == 1) {

                    firstTimePopup.setText(getResources().getString(R.string.day1_btn2_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day1_btn2_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day1_btn2_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day1_btn2_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day1_btn2_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day1_btn2_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setText(getResources().getString(R.string.day1_btn2_seventh) + "     ");
                    seventhTimePopup.setVisibility(View.VISIBLE);
                    seventhTimePopup.setTextSize(14);


                    firstHeaderPopup.setText(R.string.day1_btn2_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day1_btn2_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day1_btn2_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day1_btn2_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day1_btn2_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day1_btn2_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setText(R.string.day1_btn2_seventh_header);
                    seventhHeaderPopup.setVisibility(View.VISIBLE);
                    seventhHeaderPopup.setTextSize(16);

                    firstMeasurePopup.setVisibility(View.INVISIBLE);
                    firstMeasurePopup.setTextSize(0);

                    secondMeasurePopup.setText(R.string.day1_btn2_second_desc);
                    thirdMeasurePopup.setText(R.string.day1_btn2_third_desc);
                    forthMeasurePopup.setText(R.string.day1_btn2_forth_desc);
                    fifthMeasurePopup.setText(R.string.day1_btn2_fifth_desc);
                    sixthMeasurePopup.setText(R.string.day1_btn2_sixth_desc);
                    seventhMeasurePopup.setText(R.string.day1_btn2_seventh_desc);

                    firstAudiencePopup.setVisibility(View.INVISIBLE);
                    firstAudiencePopup.setTextSize(0);

                    secondAudiencePopup.setText(R.string.day1_btn2_second_audience);
                    thirdAudiencePopup.setText(R.string.day1_btn2_third_audience);
                    forthAudiencePopup.setText(R.string.day1_btn2_forth_audience);
                    fifthAudiencePopup.setText(R.string.day1_btn2_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day1_btn2_sixth_audience);
                    seventhAudiencePopup.setText(R.string.day1_btn2_seventh_audience);

                    firstFooterPopup.setText(R.string.day1_btn2_first_footer);
                    secondFooterPopup.setText(R.string.day1_btn2_second_footer);
                    thirdFooterPopup.setText(R.string.day1_btn2_third_footer);
                    forthFooterPopup.setText(R.string.day1_btn2_forth_footer);
                    fifthFooterPopup.setText(R.string.day1_btn2_fifth_footer);
                    sixthFooterPopup.setText(R.string.day1_btn2_sixth_footer);
                    seventhFooterPopup.setText(R.string.day1_btn2_seventh_footer);

                } else {

                    firstTimePopup.setText(getResources().getString(R.string.day2_btn2_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day2_btn2_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day2_btn2_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day2_btn2_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day2_btn2_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day2_btn2_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setVisibility(View.INVISIBLE);
                    seventhTimePopup.setTextSize(0);

                    firstHeaderPopup.setText(R.string.day2_btn2_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day2_btn2_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day2_btn2_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day2_btn2_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day2_btn2_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day2_btn2_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setVisibility(View.INVISIBLE);
                    seventhHeaderPopup.setTextSize(0);

                    firstMeasurePopup.setText(R.string.day2_btn2_first_desc);
                    secondMeasurePopup.setText(R.string.day2_btn2_second_desc);
                    thirdMeasurePopup.setText(R.string.day2_btn2_third_desc);
                    forthMeasurePopup.setText(R.string.day2_btn2_forth_desc);
                    fifthMeasurePopup.setText(R.string.day2_btn2_fifth_desc);
                    sixthMeasurePopup.setText(R.string.day2_btn2_sixth_desc);

                    seventhMeasurePopup.setVisibility(View.INVISIBLE);
                    seventhMeasurePopup.setTextSize(0);

                    firstAudiencePopup.setText(R.string.day2_btn2_first_audience);
                    secondAudiencePopup.setText(R.string.day2_btn2_second_audience);
                    thirdAudiencePopup.setText(R.string.day2_btn2_third_audience);
                    forthAudiencePopup.setText(R.string.day2_btn2_forth_audience);
                    fifthAudiencePopup.setText(R.string.day2_btn2_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day2_btn2_sixth_audience);

                    seventhAudiencePopup.setVisibility(View.INVISIBLE);
                    seventhMeasurePopup.setTextSize(0);

                    firstFooterPopup.setText(R.string.day2_btn2_first_footer);
                    secondFooterPopup.setText(R.string.day2_btn2_second_footer);
                    thirdFooterPopup.setText(R.string.day2_btn2_third_footer);
                    forthFooterPopup.setText(R.string.day2_btn2_forth_footer);
                    fifthFooterPopup.setText(R.string.day2_btn2_fifth_footer);
                    sixthFooterPopup.setText(R.string.day2_btn2_sixth_footer);

                    seventhFooterPopup.setVisibility(View.INVISIBLE);
                    seventhFooterPopup.setTextSize(0);

                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaName.setText(R.string.area_name_3);

                if (day == 1) {

                    firstTimePopup.setText(getResources().getString(R.string.day1_btn3_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day1_btn3_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day1_btn3_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day1_btn3_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day1_btn3_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day1_btn3_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setText(getResources().getString(R.string.day1_btn3_seventh) + "     ");
                    seventhTimePopup.setVisibility(View.VISIBLE);
                    seventhTimePopup.setTextSize(14);

                    firstHeaderPopup.setText(R.string.day1_btn3_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day1_btn3_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day1_btn3_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day1_btn3_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day1_btn3_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day1_btn3_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setText(R.string.day1_btn3_seventh_header);
                    seventhHeaderPopup.setVisibility(View.VISIBLE);
                    seventhHeaderPopup.setTextSize(16);


                    firstMeasurePopup.setVisibility(View.INVISIBLE);
                    firstMeasurePopup.setTextSize(0);

                    secondMeasurePopup.setText(R.string.day1_btn3_second_desc);
                    thirdMeasurePopup.setText(R.string.day1_btn3_third_desc);

                    forthMeasurePopup.setVisibility(View.INVISIBLE);
                    forthMeasurePopup.setTextSize(0);

                    fifthMeasurePopup.setVisibility(View.INVISIBLE);
                    fifthMeasurePopup.setTextSize(0);

                    sixthMeasurePopup.setText(R.string.day1_btn3_sixth_desc);
                    seventhMeasurePopup.setText(R.string.day1_btn3_seventh_desc);

                    firstAudiencePopup.setVisibility(View.INVISIBLE);
                    firstAudiencePopup.setTextSize(0);

                    secondAudiencePopup.setText(R.string.day1_btn3_second_audience);
                    thirdAudiencePopup.setText(R.string.day1_btn3_third_audience);
                    forthAudiencePopup.setText(R.string.day1_btn3_forth_audience);
                    fifthAudiencePopup.setText(R.string.day1_btn3_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day1_btn3_sixth_audience);
                    seventhAudiencePopup.setText(R.string.day1_btn3_seventh_audience);

                    firstFooterPopup.setText(R.string.day1_btn3_first_footer);
                    secondFooterPopup.setText(R.string.day1_btn3_second_footer);
                    thirdFooterPopup.setText(R.string.day1_btn3_third_footer);
                    forthFooterPopup.setText(R.string.day1_btn3_forth_footer);
                    fifthFooterPopup.setText(R.string.day1_btn3_fifth_footer);
                    sixthFooterPopup.setText(R.string.day1_btn3_sixth_footer);
                    seventhFooterPopup.setText(R.string.day1_btn3_seventh_footer);

                } else {

                    firstTimePopup.setText(getResources().getString(R.string.day2_btn3_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day2_btn3_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day2_btn3_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day2_btn3_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day2_btn3_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day2_btn3_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setVisibility(View.INVISIBLE);
                    seventhTimePopup.setTextSize(0);

                    firstHeaderPopup.setText(R.string.day2_btn3_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day2_btn3_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day2_btn3_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day2_btn3_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day2_btn3_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day2_btn3_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setVisibility(View.INVISIBLE);
                    seventhHeaderPopup.setTextSize(0);

                    firstMeasurePopup.setText(R.string.day2_btn3_first_desc);
                    secondMeasurePopup.setText(R.string.day2_btn3_second_desc);
                    thirdMeasurePopup.setText(R.string.day2_btn3_third_desc);
                    forthMeasurePopup.setText(R.string.day2_btn3_forth_desc);

                    fifthMeasurePopup.setVisibility(View.INVISIBLE);
                    fifthMeasurePopup.setTextSize(0);

                    sixthMeasurePopup.setVisibility(View.INVISIBLE);
                    sixthMeasurePopup.setTextSize(0);

                    seventhMeasurePopup.setVisibility(View.INVISIBLE);
                    seventhMeasurePopup.setTextSize(0);

                    firstAudiencePopup.setText(R.string.day2_btn3_first_audience);
                    secondAudiencePopup.setText(R.string.day2_btn3_second_audience);
                    thirdAudiencePopup.setText(R.string.day2_btn3_third_audience);
                    forthAudiencePopup.setText(R.string.day2_btn3_forth_audience);
                    fifthAudiencePopup.setText(R.string.day2_btn3_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day2_btn3_sixth_audience);

                    seventhAudiencePopup.setVisibility(View.INVISIBLE);
                    seventhAudiencePopup.setTextSize(0);

                    firstFooterPopup.setText(R.string.day2_btn3_first_footer);
                    secondFooterPopup.setText(R.string.day2_btn3_second_footer);
                    thirdFooterPopup.setText(R.string.day2_btn3_third_footer);
                    forthFooterPopup.setText(R.string.day2_btn3_forth_footer);
                    fifthFooterPopup.setText(R.string.day2_btn3_fifth_footer);
                    sixthFooterPopup.setText(R.string.day2_btn3_sixth_footer);

                    seventhFooterPopup.setVisibility(View.INVISIBLE);
                    seventhFooterPopup.setTextSize(0);

                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaName.setText(R.string.area_name_4);

                if (day == 1) {

                    firstTimePopup.setText(getResources().getString(R.string.day1_btn4_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day1_btn4_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day1_btn4_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day1_btn4_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day1_btn4_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day1_btn4_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setText(getResources().getString(R.string.day1_btn4_seventh) + "     ");
                    seventhTimePopup.setVisibility(View.VISIBLE);
                    seventhTimePopup.setTextSize(14);

                    firstHeaderPopup.setText(R.string.day1_btn4_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day1_btn4_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day1_btn4_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day1_btn4_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day1_btn4_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day1_btn4_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setText(R.string.day1_btn4_seventh_header);
                    seventhHeaderPopup.setVisibility(View.VISIBLE);
                    seventhHeaderPopup.setTextSize(16);

                    firstMeasurePopup.setVisibility(View.INVISIBLE);
                    firstMeasurePopup.setTextSize(0);

                    secondMeasurePopup.setVisibility(View.INVISIBLE);
                    secondMeasurePopup.setTextSize(0);

                    thirdMeasurePopup.setText(R.string.day1_btn4_third_desc);
                    forthMeasurePopup.setText(R.string.day1_btn4_forth_desc);
                    fifthMeasurePopup.setText(R.string.day1_btn4_fifth_desc);
                    sixthMeasurePopup.setText(R.string.day1_btn4_sixth_desc);
                    seventhMeasurePopup.setText(R.string.day1_btn4_seventh_desc);

                    firstAudiencePopup.setVisibility(View.INVISIBLE);
                    firstAudiencePopup.setTextSize(0);

                    secondAudiencePopup.setText(R.string.day1_btn4_second_audience);
                    thirdAudiencePopup.setText(R.string.day1_btn4_third_audience);
                    forthAudiencePopup.setText(R.string.day1_btn4_forth_audience);
                    fifthAudiencePopup.setText(R.string.day1_btn4_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day1_btn4_sixth_audience);
                    seventhAudiencePopup.setText(R.string.day1_btn4_seventh_audience);

                    firstFooterPopup.setText(R.string.day1_btn4_first_footer);
                    secondFooterPopup.setText(R.string.day1_btn4_second_footer);
                    thirdFooterPopup.setText(R.string.day1_btn4_third_footer);
                    forthFooterPopup.setText(R.string.day1_btn4_forth_footer);
                    fifthFooterPopup.setText(R.string.day1_btn4_fifth_footer);
                    sixthFooterPopup.setText(R.string.day1_btn4_sixth_footer);
                    seventhFooterPopup.setText(R.string.day1_btn4_seventh_footer);

                } else {

                    firstTimePopup.setText(getResources().getString(R.string.day2_btn4_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day2_btn4_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day2_btn4_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day2_btn4_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day2_btn4_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day2_btn4_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setVisibility(View.INVISIBLE);
                    seventhTimePopup.setTextSize(0);

                    firstHeaderPopup.setText(R.string.day2_btn4_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day2_btn4_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day2_btn4_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day2_btn4_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day2_btn4_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day2_btn4_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setVisibility(View.INVISIBLE);
                    seventhHeaderPopup.setTextSize(0);

                    firstMeasurePopup.setText(R.string.day2_btn4_first_desc);
                    secondMeasurePopup.setText(R.string.day2_btn4_second_desc);
                    thirdMeasurePopup.setText(R.string.day2_btn4_third_desc);
                    forthMeasurePopup.setText(R.string.day2_btn4_forth_desc);
                    fifthMeasurePopup.setText(R.string.day2_btn4_fifth_desc);
                    sixthMeasurePopup.setText(R.string.day2_btn4_sixth_desc);

                    seventhMeasurePopup.setVisibility(View.INVISIBLE);
                    seventhMeasurePopup.setTextSize(0);

                    firstAudiencePopup.setText(R.string.day2_btn4_first_audience);
                    secondAudiencePopup.setText(R.string.day2_btn4_second_audience);
                    thirdAudiencePopup.setText(R.string.day2_btn4_third_audience);
                    forthAudiencePopup.setText(R.string.day2_btn4_forth_audience);
                    fifthAudiencePopup.setText(R.string.day2_btn4_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day2_btn4_sixth_audience);

                    seventhAudiencePopup.setVisibility(View.INVISIBLE);
                    seventhAudiencePopup.setTextSize(0);

                    firstFooterPopup.setText(R.string.day2_btn4_first_footer);
                    secondFooterPopup.setText(R.string.day2_btn4_second_footer);
                    thirdFooterPopup.setText(R.string.day2_btn4_third_footer);
                    forthFooterPopup.setText(R.string.day2_btn4_forth_footer);
                    fifthFooterPopup.setText(R.string.day2_btn4_fifth_footer);
                    sixthFooterPopup.setText(R.string.day2_btn4_sixth_footer);

                    seventhFooterPopup.setVisibility(View.INVISIBLE);
                    seventhFooterPopup.setTextSize(0);

                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaName.setText(R.string.area_name_5);

                if (day == 1) {

                    firstTimePopup.setText(getResources().getString(R.string.day1_btn5_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day1_btn5_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day1_btn5_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day1_btn5_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day1_btn5_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day1_btn5_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setText(getResources().getString(R.string.day1_btn5_seventh) + "     ");
                    seventhTimePopup.setVisibility(View.VISIBLE);
                    seventhTimePopup.setTextSize(14);


                    firstHeaderPopup.setText(R.string.day1_btn5_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day1_btn5_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day1_btn5_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day1_btn5_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day1_btn5_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day1_btn5_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setText(R.string.day1_btn5_seventh_header);
                    seventhHeaderPopup.setVisibility(View.VISIBLE);
                    seventhHeaderPopup.setTextSize(16);


                    firstMeasurePopup.setText(R.string.day1_btn5_first_desc);
                    firstMeasurePopup.setVisibility(View.VISIBLE);
                    firstMeasurePopup.setTextSize(12);

                    secondMeasurePopup.setText(R.string.day1_btn5_second_desc);
                    thirdMeasurePopup.setText(R.string.day1_btn5_third_desc);
                    forthMeasurePopup.setText(R.string.day1_btn5_forth_desc);
                    fifthMeasurePopup.setText(R.string.day1_btn5_fifth_desc);

                    sixthMeasurePopup.setVisibility(View.INVISIBLE);
                    sixthMeasurePopup.setTextSize(0);

                    seventhMeasurePopup.setText(R.string.day1_btn5_seventh_desc);

                    firstAudiencePopup.setText(R.string.day1_btn5_first_audience);
                    secondAudiencePopup.setText(R.string.day1_btn5_second_audience);
                    thirdAudiencePopup.setText(R.string.day1_btn5_third_audience);
                    forthAudiencePopup.setText(R.string.day1_btn5_forth_audience);
                    fifthAudiencePopup.setText(R.string.day1_btn5_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day1_btn5_sixth_audience);
                    seventhAudiencePopup.setText(R.string.day1_btn5_seventh_audience);

                    firstFooterPopup.setText(R.string.day1_btn5_first_footer);
                    secondFooterPopup.setText(R.string.day1_btn5_second_footer);
                    thirdFooterPopup.setText(R.string.day1_btn5_third_footer);
                    forthFooterPopup.setText(R.string.day1_btn5_forth_footer);
                    fifthFooterPopup.setText(R.string.day1_btn5_fifth_footer);
                    sixthFooterPopup.setText(R.string.day1_btn5_sixth_footer);
                    seventhFooterPopup.setText(R.string.day1_btn5_seventh_footer);

                } else {

                    firstTimePopup.setText(getResources().getString(R.string.day2_btn5_first) + "     ");
                    firstTimePopup.setVisibility(View.VISIBLE);
                    firstTimePopup.setTextSize(14);

                    secondTimePopup.setText(getResources().getString(R.string.day2_btn5_second) + "     ");
                    secondTimePopup.setVisibility(View.VISIBLE);
                    secondTimePopup.setTextSize(14);

                    thirdTimePopup.setText(getResources().getString(R.string.day2_btn5_third) + "     ");
                    thirdTimePopup.setVisibility(View.VISIBLE);
                    thirdTimePopup.setTextSize(14);

                    forthTimePopup.setText(getResources().getString(R.string.day2_btn5_forth) + "     ");
                    forthTimePopup.setVisibility(View.VISIBLE);
                    forthTimePopup.setTextSize(14);

                    fifthTimePopup.setText(getResources().getString(R.string.day2_btn5_fifth) + "     ");
                    fifthTimePopup.setVisibility(View.VISIBLE);
                    fifthTimePopup.setTextSize(14);

                    sixthTimePopup.setText(getResources().getString(R.string.day2_btn5_sixth) + "     ");
                    sixthTimePopup.setVisibility(View.VISIBLE);
                    sixthTimePopup.setTextSize(14);

                    seventhTimePopup.setVisibility(View.INVISIBLE);
                    seventhTimePopup.setTextSize(0);

                    firstHeaderPopup.setText(R.string.day2_btn5_first_header);
                    firstHeaderPopup.setVisibility(View.VISIBLE);
                    firstHeaderPopup.setTextSize(16);

                    secondHeaderPopup.setText(R.string.day2_btn5_second_header);
                    secondHeaderPopup.setVisibility(View.VISIBLE);
                    secondHeaderPopup.setTextSize(16);

                    thirdHeaderPopup.setText(R.string.day2_btn5_third_header);
                    thirdHeaderPopup.setVisibility(View.VISIBLE);
                    thirdHeaderPopup.setTextSize(16);

                    forthHeaderPopup.setText(R.string.day2_btn5_forth_header);
                    forthHeaderPopup.setVisibility(View.VISIBLE);
                    forthHeaderPopup.setTextSize(16);

                    fifthHeaderPopup.setText(R.string.day2_btn5_fifth_header);
                    fifthHeaderPopup.setVisibility(View.VISIBLE);
                    fifthHeaderPopup.setTextSize(16);

                    sixthHeaderPopup.setText(R.string.day2_btn5_sixth_header);
                    sixthHeaderPopup.setVisibility(View.VISIBLE);
                    sixthHeaderPopup.setTextSize(16);

                    seventhHeaderPopup.setVisibility(View.INVISIBLE);
                    seventhHeaderPopup.setTextSize(0);

                    firstMeasurePopup.setText(R.string.day2_btn5_first_desc);
                    secondMeasurePopup.setText(R.string.day2_btn5_second_desc);
                    thirdMeasurePopup.setText(R.string.day2_btn5_third_desc);
                    forthMeasurePopup.setText(R.string.day2_btn5_forth_desc);
                    fifthMeasurePopup.setText(R.string.day2_btn5_fifth_desc);

                    sixthMeasurePopup.setVisibility(View.INVISIBLE);
                    sixthMeasurePopup.setTextSize(0);

                    seventhMeasurePopup.setVisibility(View.INVISIBLE);
                    seventhMeasurePopup.setTextSize(0);

                    firstAudiencePopup.setText(R.string.day2_btn5_first_audience);
                    secondAudiencePopup.setText(R.string.day2_btn5_second_audience);
                    thirdAudiencePopup.setText(R.string.day2_btn5_third_audience);
                    forthAudiencePopup.setText(R.string.day2_btn5_forth_audience);
                    fifthAudiencePopup.setText(R.string.day2_btn5_fifth_audience);
                    sixthAudiencePopup.setText(R.string.day2_btn5_sixth_audience);

                    seventhAudiencePopup.setVisibility(View.INVISIBLE);
                    seventhAudiencePopup.setTextSize(0);

                    firstFooterPopup.setText(R.string.day2_btn5_first_footer);
                    secondFooterPopup.setText(R.string.day2_btn5_second_footer);
                    thirdFooterPopup.setText(R.string.day2_btn5_third_footer);
                    forthFooterPopup.setText(R.string.day2_btn5_forth_footer);
                    fifthFooterPopup.setText(R.string.day2_btn5_fifth_footer);
                    sixthFooterPopup.setText(R.string.day2_btn5_sixth_footer);

                    seventhFooterPopup.setVisibility(View.INVISIBLE);
                    seventhFooterPopup.setTextSize(0);

                }
            }
        });




        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(android.R.color.transparent)));
        popupWindow.setOutsideTouchable(true);

        if (day == 1) {

            firstTimePopup.setText(getResources().getString(R.string.day1_btn1_first) + "     ");
            firstTimePopup.setVisibility(View.VISIBLE);
            firstTimePopup.setTextSize(14);

            secondTimePopup.setText(getResources().getString(R.string.day1_btn1_second) + "     ");
            secondTimePopup.setVisibility(View.VISIBLE);
            secondTimePopup.setTextSize(14);

            thirdTimePopup.setText(getResources().getString(R.string.day1_btn1_third) + "     ");
            thirdTimePopup.setVisibility(View.VISIBLE);
            thirdTimePopup.setTextSize(14);

            forthTimePopup.setText(getResources().getString(R.string.day1_btn1_forth) + "     ");
            forthTimePopup.setVisibility(View.VISIBLE);
            forthTimePopup.setTextSize(14);

            fifthTimePopup.setText(getResources().getString(R.string.day1_btn1_fifth) + "     ");
            fifthTimePopup.setVisibility(View.VISIBLE);
            fifthTimePopup.setTextSize(14);

            sixthTimePopup.setText(getResources().getString(R.string.day1_btn1_sixth) + "     ");
            sixthTimePopup.setVisibility(View.VISIBLE);
            sixthTimePopup.setTextSize(14);

            seventhTimePopup.setText(getResources().getString(R.string.day1_btn1_seventh) + "     ");
            seventhTimePopup.setVisibility(View.VISIBLE);
            seventhTimePopup.setTextSize(14);

            firstHeaderPopup.setText(R.string.day1_btn1_first_header);
            firstHeaderPopup.setVisibility(View.VISIBLE);
            firstHeaderPopup.setTextSize(16);

            secondHeaderPopup.setText(R.string.day1_btn1_second_header);
            secondHeaderPopup.setVisibility(View.VISIBLE);
            secondHeaderPopup.setTextSize(16);

            thirdHeaderPopup.setText(R.string.day1_btn1_third_header);
            thirdHeaderPopup.setVisibility(View.VISIBLE);
            thirdHeaderPopup.setTextSize(16);

            forthHeaderPopup.setText(R.string.day1_btn1_forth_header);
            forthHeaderPopup.setVisibility(View.VISIBLE);
            forthHeaderPopup.setTextSize(16);

            fifthHeaderPopup.setText(R.string.day1_btn1_fifth_header);
            fifthHeaderPopup.setVisibility(View.VISIBLE);
            fifthHeaderPopup.setTextSize(16);

            sixthHeaderPopup.setText(R.string.day1_btn1_sixth_header);
            sixthHeaderPopup.setVisibility(View.VISIBLE);
            sixthHeaderPopup.setTextSize(16);

            seventhHeaderPopup.setText(R.string.day1_btn1_seventh_header);
            seventhHeaderPopup.setVisibility(View.VISIBLE);
            seventhHeaderPopup.setTextSize(16);

            firstMeasurePopup.setVisibility(View.INVISIBLE);
            firstMeasurePopup.setTextSize(0);

            secondMeasurePopup.setText(R.string.day1_btn1_second_desc);
            thirdMeasurePopup.setText(R.string.day1_btn1_third_desc);
            forthMeasurePopup.setText(R.string.day1_btn1_forth_desc);
            fifthMeasurePopup.setText(R.string.day1_btn1_fifth_desc);
            sixthMeasurePopup.setText(R.string.day1_btn1_sixth_desc);
            seventhMeasurePopup.setText(R.string.day1_btn1_seventh_desc);

            firstAudiencePopup.setVisibility(View.INVISIBLE);
            firstAudiencePopup.setTextSize(0);

            secondAudiencePopup.setText(R.string.day1_btn1_second_audience);
            thirdAudiencePopup.setText(R.string.day1_btn1_third_audience);
            forthAudiencePopup.setText(R.string.day1_btn1_forth_audience);
            fifthAudiencePopup.setText(R.string.day1_btn1_fifth_audience);
            sixthAudiencePopup.setText(R.string.day1_btn1_sixth_audience);
            seventhAudiencePopup.setText(R.string.day1_btn1_seventh_audience);

            firstFooterPopup.setVisibility(View.INVISIBLE);
            firstFooterPopup.setTextSize(0);

            secondFooterPopup.setText(R.string.day1_btn1_second_footer);
            thirdFooterPopup.setText(R.string.day1_btn1_third_footer);
            forthFooterPopup.setText(R.string.day1_btn1_forth_footer);
            fifthFooterPopup.setText(R.string.day1_btn1_fifth_footer);
            sixthFooterPopup.setText(R.string.day1_btn1_sixth_footer);
            seventhFooterPopup.setText(R.string.day1_btn1_seventh_footer);
        } else {

            firstTimePopup.setText(getResources().getString(R.string.day2_btn1_first) + "     ");
            firstTimePopup.setVisibility(View.VISIBLE);
            firstTimePopup.setTextSize(14);

            secondTimePopup.setText(getResources().getString(R.string.day2_btn1_second) + "     ");
            secondTimePopup.setVisibility(View.VISIBLE);
            secondTimePopup.setTextSize(14);

            thirdTimePopup.setText(getResources().getString(R.string.day2_btn1_third) + "     ");
            thirdTimePopup.setVisibility(View.VISIBLE);
            thirdTimePopup.setTextSize(14);

            forthTimePopup.setText(getResources().getString(R.string.day2_btn1_forth) + "     ");
            forthTimePopup.setVisibility(View.VISIBLE);
            forthTimePopup.setTextSize(14);

            fifthTimePopup.setText(getResources().getString(R.string.day2_btn1_fifth) + "     ");
            fifthTimePopup.setVisibility(View.VISIBLE);
            fifthTimePopup.setTextSize(14);

            sixthTimePopup.setText(getResources().getString(R.string.day2_btn1_sixth) + "     ");
            sixthTimePopup.setVisibility(View.VISIBLE);
            sixthTimePopup.setTextSize(14);

            seventhTimePopup.setText(getResources().getString(R.string.day2_btn1_seventh) + "     ");
            seventhTimePopup.setVisibility(View.VISIBLE);
            seventhTimePopup.setTextSize(14);

            firstHeaderPopup.setText(R.string.day2_btn1_first_header);
            secondHeaderPopup.setText(R.string.day2_btn1_second_header);
            thirdHeaderPopup.setText(R.string.day2_btn1_third_header);
            forthHeaderPopup.setText(R.string.day2_btn1_forth_header);
            fifthHeaderPopup.setText(R.string.day2_btn1_fifth_header);
            sixthHeaderPopup.setText(R.string.day2_btn1_sixth_header);
            seventhHeaderPopup.setText(R.string.day2_btn1_seventh_header);
            seventhTimePopup.setVisibility(View.VISIBLE);

            firstMeasurePopup.setText(R.string.day2_btn1_first_desc);
            secondMeasurePopup.setText(R.string.day2_btn1_second_desc);
            thirdMeasurePopup.setText(R.string.day2_btn1_third_desc);
            forthMeasurePopup.setText(R.string.day2_btn1_forth_desc);
            fifthMeasurePopup.setText(R.string.day2_btn1_fifth_desc);
            sixthMeasurePopup.setText(R.string.day2_btn1_sixth_desc);
            seventhMeasurePopup.setText(R.string.day2_btn1_seventh_desc);
            seventhTimePopup.setVisibility(View.VISIBLE);

            firstAudiencePopup.setText(R.string.day2_btn1_first_audience);
            secondAudiencePopup.setText(R.string.day2_btn1_second_audience);
            thirdAudiencePopup.setText(R.string.day2_btn1_third_audience);
            forthAudiencePopup.setText(R.string.day2_btn1_forth_audience);
            fifthAudiencePopup.setText(R.string.day2_btn1_fifth_audience);
            sixthAudiencePopup.setText(R.string.day2_btn1_sixth_audience);
            seventhAudiencePopup.setText(R.string.day2_btn1_seventh_audience);
            seventhTimePopup.setVisibility(View.VISIBLE);

            firstFooterPopup.setText(R.string.day2_btn1_first_footer);
            secondFooterPopup.setText(R.string.day2_btn1_second_footer);
            thirdFooterPopup.setText(R.string.day2_btn1_third_footer);
            forthFooterPopup.setText(R.string.day2_btn1_forth_footer);
            fifthFooterPopup.setText(R.string.day2_btn1_fifth_footer);
            sixthFooterPopup.setText(R.string.day2_btn1_sixth_footer);
            seventhFooterPopup.setText(R.string.day2_btn1_seventh_footer);
            seventhTimePopup.setVisibility(View.VISIBLE);

        }

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

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
