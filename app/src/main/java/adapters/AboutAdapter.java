package adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private String text;


    public AboutAdapter(String text, Context mContext) {
        this.mContext = mContext;
        this.text = text;
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about, parent, false);

        return new AboutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutViewHolder holder, int position) {
        Log.i("KEKE", text);
        holder.title.setText(text);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class AboutViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView title;


        public AboutViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.desc);


        }
    }


}
