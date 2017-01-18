package ru.velkonost.robofest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;


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

        switch (photo) {
            case 1:
                imageView.setImageDrawable(ContextCompat
                        .getDrawable(FullScreenPhotoActivity.this, R.drawable.robofestomsk_sheme));

                setTitle("Схема проведения");
                break;

        }

        new PhotoViewAttacher(imageView);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
