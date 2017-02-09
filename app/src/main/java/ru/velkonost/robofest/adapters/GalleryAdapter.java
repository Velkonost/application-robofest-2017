package ru.velkonost.robofest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ru.velkonost.robofest.R;

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
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_gallery, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_gal);

        imgUrls = new String[imageUrls.size()];

        for (int i = 0; i < imageUrls.size(); i++) {
            imgUrls[i] = imageUrls.get(i);
        }


        Glide
                .with(context)
                .load(imgUrls[position])
                .into(imageView);

        return convertView;
    }
}
