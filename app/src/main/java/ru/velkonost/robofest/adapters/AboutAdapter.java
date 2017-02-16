package ru.velkonost.robofest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.velkonost.robofest.R;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.AboutViewHolder> {

    private List<String> data;
    private Context mContext;
    private String title;
    private String textHistory;
    private String text;


    public AboutAdapter(String textHistory, String text, Context mContext) {
        this.mContext = mContext;
        this.textHistory = textHistory;
        this.text = text;
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about, parent, false);

        return new AboutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutViewHolder holder, int position) {
        holder.desc.setText(textHistory);
        holder.desc2.setText(text);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class AboutViewHolder extends RecyclerView.ViewHolder {

        TextView desc;
        TextView desc2;


        public AboutViewHolder(View itemView) {
            super(itemView);

            desc = (TextView) itemView.findViewById(R.id.desc);
            desc2 = (TextView) itemView.findViewById(R.id.desc2);


        }
    }
}
