package adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.velkonost.robofest.R;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder> {

    private List<String> data;
    private Context mContext;
    private String title;
    private ImageView compImg;

    public CompetitionAdapter(String title, Context mContext) {
        this.mContext = mContext;
        this.title = title;
    }

    @Override
    public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition, parent, false);

        return new CompetitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetitionViewHolder holder, int position) {
        holder.title.setText(title);
        holder.title.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class CompetitionViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView compImg;
        TextView title;


        public CompetitionViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.Text);
            compImg = (ImageView) itemView.findViewById(R.id.comp_img);


        }
    }
}
