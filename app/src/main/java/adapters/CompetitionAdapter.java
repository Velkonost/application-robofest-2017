package adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ru.velkonost.robofest.R;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder> {

    private List<String> data;
    private Context mContext;
    private String text;
    private ImageView compImg;
    public static String text2;
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
        //holder.title.setText(text);
        holder.title.setText(text);
        holder.title.setTextSize(0);

        if (!holder.isLangProg){
            holder.langProgTxt.setTextSize(0);
            holder.langProgTxtName.setTextSize(0);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class CompetitionViewHolder extends RecyclerView.ViewHolder {


        CardView cardView;
        ImageView compImg, showMoreImg;
        TextView title, ageTxt, teamTxt, robotTxt, langProgTxt, langProgTxtName, url1, url2;
        Button showMoreBtn;
        String goURL = null;
        boolean check = false;

        boolean isLangProg = true;


        public CompetitionViewHolder(View itemView) {
            super(itemView);

            ageTxt = (TextView) itemView.findViewById(R.id.text_age);
            teamTxt = (TextView) itemView.findViewById(R.id.text_team);
            robotTxt = (TextView) itemView.findViewById(R.id.text_robot);
            langProgTxt = (TextView) itemView.findViewById(R.id.text_langProg);
            langProgTxtName = (TextView) itemView.findViewById(R.id.text_langProg_name);

            cardView = (CardView) itemView.findViewById(R.id.cardViewCompetition);

            title = (TextView) itemView.findViewById(R.id.title_comp);
            url1 = (TextView) itemView.findViewById(R.id.url1);
            url2 = (TextView) itemView.findViewById(R.id.url2);

            compImg = (ImageView) itemView.findViewById(R.id.comp_img);
            showMoreBtn = (Button) itemView.findViewById(R.id.btn_showmore);
            showMoreImg = (ImageView) itemView.findViewById(R.id.img_showmore);

            url1.setMovementMethod(LinkMovementMethod.getInstance());
            url2.setMovementMethod(LinkMovementMethod.getInstance());

            switch (competitionId) {
                case 1:
                    ageTxt.setText(R.string.fll_age);
                    teamTxt.setText(R.string.fll_team);
                    robotTxt.setText(R.string.fll_robot);
                    langProgTxt.setText(R.string.fll_langProg);
                    goURL = "http://robofest.ru/sorevnovaniya/FLL/";
                    compImg.setImageResource(R.drawable.fll);

                  /*  url1.setTextSize(14);
                    url2.setTextSize(14);*/
                    url1.setText(Html.fromHtml(itemView.getResources().getString(R.string.url1_fll)));
                    url2.setText(Html.fromHtml(itemView.getResources().getString(R.string.url2_fll)));

                    break;
                case 2:
                    ageTxt.setText(R.string.jrfll_age);
                    teamTxt.setText(R.string.jrfll_team);
                    robotTxt.setText(R.string.jrfll_robot);
                    langProgTxtName.setVisibility(View.INVISIBLE);
                    langProgTxt.setVisibility(View.INVISIBLE);
                    goURL = "http://robofest.ru/sorevnovaniya/JrFLL/";
                    compImg.setImageResource(R.drawable.jrfll);

                  /*  url1.setTextSize(14);
                    url2.setTextSize(0);*/
                    url1.setText(Html.fromHtml(itemView.getResources().getString(R.string.url1_jrfll)));

                    isLangProg = false;

                    break;
                case 3:
                    ageTxt.setText(R.string.hr_age);
                    teamTxt.setText(R.string.hr_team);
                    robotTxt.setText(R.string.hr_robot);
                    langProgTxt.setText(R.string.hr_langProg);
                    goURL = "http://robofest.ru/sorevnovaniya/HR/";
                    compImg.setImageResource(R.drawable.hr);

                 /*   url1.setTextSize(14);
                    url2.setTextSize(0);*/
                    url1.setText(Html.fromHtml(itemView.getResources().getString(R.string.url1_hr)));

                    break;
                case 4:
                    ageTxt.setText(R.string.robocarusel_age);
                    teamTxt.setText(R.string.robocarusel_team);
                    robotTxt.setText(R.string.robocarusel_robot);
                    langProgTxt.setText(R.string.robocarusel_langProg);
                    goURL = "http://robofest.ru/sorevnovaniya/robokarusel/";
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) compImg.getLayoutParams();
                    params.height = 0;
                    compImg.setLayoutParams(params);
                  /*  url1.setTextSize(14);
                    url2.setTextSize(14);*/
                    url1.setText(Html.fromHtml(itemView.getResources().getString(R.string.url1_robocarusel)));
                    url2.setText(Html.fromHtml(itemView.getResources().getString(R.string.url2_robocarusel)));
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
                        title.setVisibility(View.VISIBLE);
                        title.setTextSize(14);


                        url1.setVisibility(View.VISIBLE);
                        url1.setTextSize(14);

                        url2.setVisibility(View.VISIBLE);
                        url2.setTextSize(14);

                        showMoreImg.setImageResource(R.drawable.icon_btn_showmore2);
                //        title.setText(text2);
                    }else{
                        title.setVisibility(View.INVISIBLE);
                        title.setTextSize(0);

                        url1.setVisibility(View.INVISIBLE);
                        url1.setTextSize(0);

                        url2.setVisibility(View.INVISIBLE);
                        url2.setTextSize(0);

                        showMoreImg.setImageResource(R.drawable.icon_btn_showmore);
                //        title.setText("");
                    }
                }
            };

            showMoreBtn.setOnClickListener(oclBtnOk);
        }
    }
}
