package ru.velkonost.robofest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import ru.velkonost.robofest.adapters.RecyclerView_Adapter;
import ru.velkonost.robofest.model.Data_Model;

/**
 * Created by Andrey on 09.02.2017.
 */

public class RecyclerView_Activity extends AppCompatActivity {
    private static RecyclerView recyclerView;

    //String and Integer array for Recycler View Items
    public static final ArrayList<String> TITLES = new ArrayList<String>();
    public static final Integer[] IMAGES = {R.drawable.background_about};


    private static String navigateFrom;//String to get Intent Value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_gallery);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Галерея");*/
        initViews();
        populatRecyclerView();
    }

    // Initialize the view
    private void initViews() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Set Back Icon on Activity

       // navigateFrom = getIntent().getStringExtra("navigateFrom");//Get Intent Value in String

        recyclerView = (RecyclerView)
                findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        //Set RecyclerView type according to intent value
          //  getSupportActionBar().setTitle("Staggered GridLayout Manager");
            recyclerView
                    .setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));// Here 2 is no. of columns to be displayed
    }


    // populate the list view by adding data to arraylist
    private void populatRecyclerView() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 62; i++) {
            TITLES.add("http://robofestomsk.ru/images/robofest2017_photo/bor"+i+".jpg");
            //TITLES.add("http://robofestomsk.ru/images/robofest2017_photo/thumb/bor_the"+i+".jpg");
        }
        for (int i = 0; i < TITLES.size(); i++) {
            arrayList.add(TITLES.get(i));
        }
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(RecyclerView_Activity.this, arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview
        adapter.notifyDataSetChanged();// Notify the adapter

    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}