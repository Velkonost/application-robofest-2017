package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ru.velkonost.robofest.R;

/**
 * Created by Andrey on 27.01.2017.
 */
public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.TranslationViewHolder> {

    private List<String> data;
    private Context mContext;
    private String text;
    private ImageView compImg;
    public static String text2;
    private static int translationId;

    public TranslationAdapter(int translationId, String title, Context mContext) {
        this.mContext = mContext;
        this.text = title;
        this.translationId = translationId;
    }

    @Override
    public TranslationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TranslationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TranslationViewHolder extends RecyclerView.ViewHolder{
        public TranslationViewHolder(View itemView) {
            super(itemView);
        }
    }
}
