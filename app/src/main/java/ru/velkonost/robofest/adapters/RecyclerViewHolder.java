package ru.velkonost.robofest.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.velkonost.robofest.R;

/**
 * Created by Andrey on 09.02.2017.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView imageview;
    public CardView cardView;

    public RecyclerViewHolder(View view) {
        super(view);
        // Find all views ids

        this.title = (TextView) view
                .findViewById(R.id.title);
        this.imageview = (ImageView) view
                .findViewById(R.id.image);
        this.cardView = (CardView) view
                .findViewById(R.id.card_view);


    }


}
