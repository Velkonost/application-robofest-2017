package ru.velkonost.robofest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import ru.velkonost.robofest.adapters.GalleryAdapter;


public class GalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<String> arrayList2 = new ArrayList<>();
    public String urlToOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        gridView = (GridView) findViewById(R.id.gvMain);

        for(int i = 0; i<62; i++) {
                /*arrayList.add(new GalleryImage( "http://robofestomsk.ru/images/robofest2017_photo/bor"+i+".jpg",
                                                "http://robofestomsk.ru/images/robofest2017_photo/bor"+(i+1)+".jpg",
                                                "http://robofestomsk.ru/images/robofest2017_photo/bor"+(i+2)+".jpg",
                                                "http://robofestomsk.ru/images/robofest2017_photo/bor"+(i+3)+".jpg",
                                                "http://robofestomsk.ru/images/robofest2017_photo/bor"+(i+4)+".jpg"));*/
            arrayList2.add("http://robofestomsk.ru/images/robofest2017_photo/thumb/bor_the"+i+".jpg");
        }
        gridView.setAdapter(new GalleryAdapter(this, arrayList2));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                urlToOpen = "http://robofestomsk.ru/images/robofest2017_photo/bor"+i+".jpg";
                openPhoto(urlToOpen);
            }
        });



    }

    public void openPhoto(String url) {

        Intent intent = new Intent(GalleryActivity.this, FullScreenPhotoActivity.class);
        intent.putExtra("Photo", 5);
        intent.putExtra("url", url);
        GalleryActivity.this.startActivity(intent);

    }
}