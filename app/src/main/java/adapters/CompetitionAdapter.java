package adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.velkonost.robofest.R;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder> {

    private List<String> data;
    private Context mContext;
    private String text;
    private ImageView compImg;
    private static int competitionId;

    public CompetitionAdapter(int competitionId, String title, Context mContext) {
        this.mContext = mContext;
        this.text = title;
        this.competitionId = competitionId;
    }

    @Override
    public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition, parent, false);

        return new CompetitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetitionViewHolder holder, int position) {
        holder.title.setText(text);
  //      holder.title.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class CompetitionViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView compImg;
        TextView title;
        WebView webView;
        Button showMoreBtn;
        String goURL = null;
        boolean check = false;


        public CompetitionViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardViewCompetition);
            title = (TextView) itemView.findViewById(R.id.title_comp);
            compImg = (ImageView) itemView.findViewById(R.id.comp_img);
            webView = (WebView) itemView.findViewById(R.id.showmore_web);
            showMoreBtn = (Button) itemView.findViewById(R.id.btn_showmore);

            webView.getSettings().setJavaScriptEnabled(true);

            switch (competitionId) {
                case 1:
                    goURL = "http://robofest.ru/sorevnovaniya/FLL/";
                    compImg.setImageResource(R.drawable.fll);
                    break;
                case 2:
                    goURL = "http://robofest.ru/sorevnovaniya/JrFLL/";
                    compImg.setImageResource(R.drawable.jrfll);
                    break;
                case 3:
                    goURL = "http://robofest.ru/sorevnovaniya/HR/";
                    compImg.setImageResource(R.drawable.hr);
                    break;
                case 4:
                    break;
                default:

                    //   rv.setAdapter(new CompetitionAdapter("HRwrehw;o", getContext()));
                    break;
            }

            View.OnClickListener oclBtnOk = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check=!check;
                    if(check) {
                        webView.setVisibility(View.VISIBLE);
                        webView.loadUrl(goURL);
                        webView.getSettings().setBuiltInZoomControls(true);
                    /*    webView.setInitialScale(1);
                        webView.getSettings().setLoadWithOverviewMode(true);
                        webView.getSettings().setUseWideViewPort(true);*/
                    }else{
                        webView.setVisibility(View.INVISIBLE);
                        webView.stopLoading();
                    }
                }
            };

            showMoreBtn.setOnClickListener(oclBtnOk);
        }
    }
}
