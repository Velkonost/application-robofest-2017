package ru.velkonost.robofest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Andrey on 09.02.2017.
 */

public class GalleryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        OpenRecyclerViewActivity("staggered");
    }

    private void OpenRecyclerViewActivity(String navigateFrom){
        Intent in = new Intent(GalleryActivity.this, RecyclerView_Activity.class);
        in.putExtra("navigateFrom",navigateFrom);
        startActivity(in);
    }
}
