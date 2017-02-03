package ru.velkonost.robofest;


import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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
                Display display = getWindowManager().getDefaultDisplay();
                Point sizeP = new Point();
                display.getSize(sizeP);
                int width = sizeP.x;
                int height = sizeP.y;

                Glide.with(FullScreenPhotoActivity.this)
                        .load("http://www.robofestomsk.ru/images/robofestomsk_sheme.jpg")
                        .placeholder(R.mipmap.ic_launcher)
                        .override(width, 200)
                        .into(imageView);


                setTitle("Схема проведения");
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
