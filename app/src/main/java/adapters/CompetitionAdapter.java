package adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }

    public static class CompetitionViewHolder extends RecyclerView.ViewHolder {


        CardView cardView;
        ImageView compImg;
        TextView title, ageTxt, teamTxt, robotTxt, langProgTxt, langProgTxtName;
        Button showMoreBtn;
        String goURL = null;
        boolean check = false;


        public CompetitionViewHolder(View itemView) {
            super(itemView);

            ageTxt = (TextView) itemView.findViewById(R.id.text_age);
            teamTxt = (TextView) itemView.findViewById(R.id.text_team);
            robotTxt = (TextView) itemView.findViewById(R.id.text_robot);
            langProgTxt = (TextView) itemView.findViewById(R.id.text_langProg);
            langProgTxtName = (TextView) itemView.findViewById(R.id.text_langProg_name);

            cardView = (CardView) itemView.findViewById(R.id.cardViewCompetition);
            title = (TextView) itemView.findViewById(R.id.title_comp);
            compImg = (ImageView) itemView.findViewById(R.id.comp_img);
            showMoreBtn = (Button) itemView.findViewById(R.id.btn_showmore);


            switch (competitionId) {
                case 1:
                    ageTxt.setText(R.string.fll_age);
                    teamTxt.setText(R.string.fll_team);
                    robotTxt.setText(R.string.fll_robot);
                    langProgTxt.setText(R.string.fll_langProg);
                    goURL = "http://robofest.ru/sorevnovaniya/FLL/";
                    compImg.setImageResource(R.drawable.fll);
                    break;
                case 2:
                    ageTxt.setText(R.string.jrfll_age);
                    teamTxt.setText(R.string.jrfll_team);
                    robotTxt.setText(R.string.jrfll_robot);
                    langProgTxtName.setVisibility(View.INVISIBLE);
                    langProgTxt.setVisibility(View.INVISIBLE);
                    goURL = "http://robofest.ru/sorevnovaniya/JrFLL/";
                    compImg.setImageResource(R.drawable.jrfll);

                    break;
                case 3:
                    ageTxt.setText(R.string.hr_age);
                    teamTxt.setText(R.string.hr_team);
                    robotTxt.setText(R.string.hr_robot);
                    langProgTxt.setText(R.string.hr_langProg);
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
                        title.setVisibility(View.VISIBLE);
                        title.setTextSize(14);
                //        title.setText(text2);
                    }else{
                        title.setVisibility(View.INVISIBLE);
                        title.setTextSize(0);
                //        title.setText("");
                    }
                }
            };

            showMoreBtn.setOnClickListener(oclBtnOk);
        }
    }
}
