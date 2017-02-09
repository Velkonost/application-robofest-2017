package ru.velkonost.robofest.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import ru.velkonost.robofest.R;

/**
 * Created by Andrey on 08.02.2017.
 */

public class GalleryAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;

    private ArrayList<String> imageUrls;
    private String[] imgUrls;

    public GalleryAdapter(Context context, ArrayList<String> imageUrls) {
        super(context, R.layout.item_gallery, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_gallery, parent, false);
        }

        imgUrls = new String[imageUrls.size()];

        for (int i = 0; i < imageUrls.size(); i++) {
            imgUrls[i] = imageUrls.get(i).toString();
        }


        Glide
                .with(context)
                .load(imgUrls[position])
                .into((ImageView) convertView);

        return convertView;
    }
}
