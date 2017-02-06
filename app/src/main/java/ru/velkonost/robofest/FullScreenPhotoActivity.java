package ru.velkonost.robofest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;


public class FullScreenPhotoActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_full_screen_photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Intent intent = getIntent();

        int photo = intent.getExtras().getInt("Photo");

        ImageView imageView = (ImageView) findViewById(R.id.fullImage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switch (photo) {
            case 1:

                imageView.setImageDrawable(getResources().getDrawable(R.drawable.map));

                setTitle("Схема проведения");
                break;
            case 2:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.graphic));

                setTitle("Программа соревнований");
                break;

            case 3:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.day1));

                setTitle("17 февраля");
                break;

            case 4:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.day2));

                setTitle("18 февраля");
                break;
        }

        new PhotoViewAttacher(imageView);


        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
