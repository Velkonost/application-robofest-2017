package fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import ru.velkonost.robofest.R;

public class SecondMainFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_second_main;

    private int competitionId;

    private String textHistory1;
    private String text1;

    private String textHistory2;
    private String text2;

    private String textHistory3;
    private String text3;


    private String textHistory4;
    private String text4;

    private Document doc = null, doc2 = null;

    private boolean first = true, second = true, third = true, forth = true;

    public static SecondMainFragment getInstance(Context context, String title) {
        Bundle args = new Bundle();
        SecondMainFragment fragment = new SecondMainFragment();

        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(title);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        competitionId = 1;
        new GetHtml().execute(1);
        competitionId = 2;
        new GetHtml().execute(2);
        competitionId = 3;
        new GetHtml().execute(3);
        competitionId = 4;
        new GetHtml().execute(4);

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private class GetHtml extends AsyncTask<Object, Object, String> {
        @Override
        protected String doInBackground(Object... strings) {

            int id = (int) strings[0];
            /**
             * Формирование адреса, по которому необходимо обратиться.
             **/
            String dataURL = "http://developer.alexanderklimov.ru/android/";
            String goURL = null;

            switch (id) {
                case 1:
                    goURL = "http://robofest.ru/sorevnovaniya/FLL/";
                    break;
                case 2:
                    goURL = "http://robofest.ru/sorevnovaniya/JrFLL/";
                    break;
                case 3:
                    goURL = "http://robofest.ru/sorevnovaniya/HR/";
                    break;
                case 4:
                    goURL = "http://robofest.ru/sorevnovaniya/robokarusel/";
                    break;
                default:

                    break;
            }
            /**
             * Формирование отправных данных.
             */

            try {
                doc = Jsoup.connect(goURL).get();
                doc2 = Jsoup.connect(goURL).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return String.valueOf(id);
        }
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            int id = Integer.parseInt(strJson);

            CardView cardView;
            TextView title, ageTxt, teamTxt, robotTxt, langProgTxt, langProgTxtName, url1, url2;
            Button showMoreBtn;
            String goURL = null;
            boolean check = false;

            boolean isLangProg = true;


            //List title = doc.select("article[class=box post]").select("p");
            //textHistory = doc.select("div.content").text();
            //text = doc.select("div.content").text();

            List title2 = doc.select("div.content-wrapper").select("div.content").select("p");

            textHistory1 = "";
            text1 = "";

            textHistory2 = "";
            text2 = "";

            textHistory3 = "";
            text3 = "";

            textHistory4 = "";
            text4 = "";


            switch (id) {
                case 1:
                    text1 = TextUtils.join(" ", title2.subList(7, 9));
                    text1 += "\n";
                    text1 = "First Lego League (FLL)\n"+text1+"\n"+""+"\n";
                    // text+=getResources().getString(R.string.url_fll);
                    // text+="\n"+getResources().getString(R.string.url1_fll)+"\n"+getResources().getString(R.string.url2_fll)+"\n";
                    break;
                case 2:
                    text2 = TextUtils.join(" ", title2.subList(6, 7));
                    text2 = "Junior First Lego League (Jr.FLL)\n"+text2;
                    text2+="\n";
                    text2+= TextUtils.join(" ", title2.subList(8, 9))+"\n";
                    //   text+=getResources().getString(R.string.url_jrfll)+"\n";
                    break;
                case 3:
                    text3 = TextUtils.join(" ", title2.subList(3, 3));
                    text3 = "HELLO, ROBOT!\n"+text3;
                    text3+="\n";
                    text3+= TextUtils.join(" ", title2.subList(3, 8));
                    text3+="\n";
                    text3+= TextUtils.join(" ", title2.subList(5, 8))+"\n";
                 /*   text+=getResources().getString(R.string.url_hr)+"\n";
                    text+="\n"+getResources().getString(R.string.url1_hr)+"\n";*/
                    break;
                case 4:
                    text4 = "РОБОКАРУСЕЛЬ!\n"+text4;
                    text4+="\n";
                    text4+="\n";
                /*    text+="\n"+getResources().getString(R.string.url1_robocarusel)+"\n";
                    text+="\n"+getResources().getString(R.string.url_robocarusel)+"\n";
                    text+="\n"+getResources().getString(R.string.url2_robocarusel)+"\n";*/
                    break;
                default:

                    //   rv.setAdapter(new CompetitionAdapter("HRwrehw;o", getContext()));
                    break;
            }



            //textHistory = TextUtils.join(" ", title.subList(1, 5));
            //text = TextUtils.join(" ", title.subList(1, 8));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textHistory1 = String.valueOf(Html.fromHtml(textHistory1, Html.FROM_HTML_MODE_LEGACY));
                text1 = String.valueOf(Html.fromHtml(text1, Html.FROM_HTML_MODE_LEGACY));

                textHistory2 = String.valueOf(Html.fromHtml(textHistory2, Html.FROM_HTML_MODE_LEGACY));
                text2 = String.valueOf(Html.fromHtml(text2, Html.FROM_HTML_MODE_LEGACY));

                textHistory3 = String.valueOf(Html.fromHtml(textHistory3, Html.FROM_HTML_MODE_LEGACY));
                text3 = String.valueOf(Html.fromHtml(text3, Html.FROM_HTML_MODE_LEGACY));

                textHistory4 = String.valueOf(Html.fromHtml(textHistory4, Html.FROM_HTML_MODE_LEGACY));
                text4 = String.valueOf(Html.fromHtml(text4, Html.FROM_HTML_MODE_LEGACY));
            } else {
                textHistory1 = String.valueOf(Html.fromHtml(textHistory1));
                text1 = String.valueOf(Html.fromHtml(text1));

                textHistory2 = String.valueOf(Html.fromHtml(textHistory2));
                text2 = String.valueOf(Html.fromHtml(text2));

                textHistory3 = String.valueOf(Html.fromHtml(textHistory3));
                text3 = String.valueOf(Html.fromHtml(text3));

                textHistory4 = String.valueOf(Html.fromHtml(textHistory4));
                text4 = String.valueOf(Html.fromHtml(text4));
            }

            switch (id) {
                case 1:
                    ageTxt = (TextView) view.findViewById(R.id.text_age1);
                    teamTxt = (TextView) view.findViewById(R.id.text_team1);
                    robotTxt = (TextView) view.findViewById(R.id.text_robot1);
                    langProgTxt = (TextView) view.findViewById(R.id.text_langProg1);
                    langProgTxtName = (TextView) view.findViewById(R.id.text_langProg_name1);

                    cardView = (CardView) view.findViewById(R.id.cardView1);

                    title = (TextView) view.findViewById(R.id.title_comp1);
                    url1 = (TextView) view.findViewById(R.id.url11);
                    url2 = (TextView) view.findViewById(R.id.url21);

                    url1.setMovementMethod(LinkMovementMethod.getInstance());
                    url2.setMovementMethod(LinkMovementMethod.getInstance());



                    ageTxt.setText(R.string.fll_age);
                    teamTxt.setText(R.string.fll_team);
                    robotTxt.setText(R.string.fll_robot);
                    langProgTxt.setText(R.string.fll_langProg);
                    goURL = "http://robofest.ru/sorevnovaniya/FLL/";

                  /*  url1.setTextSize(14);
                    url2.setTextSize(14);*/
                    url1.setText(Html.fromHtml(view.getResources().getString(R.string.url1_fll)));
                    url2.setText(Html.fromHtml(view.getResources().getString(R.string.url2_fll)));

                    break;
                case 2:
                    ageTxt = (TextView) view.findViewById(R.id.text_age2);
                    teamTxt = (TextView) view.findViewById(R.id.text_team2);
                    robotTxt = (TextView) view.findViewById(R.id.text_robot2);
                    langProgTxt = (TextView) view.findViewById(R.id.text_langProg2);
                    langProgTxtName = (TextView) view.findViewById(R.id.text_langProg_name2);

                    cardView = (CardView) view.findViewById(R.id.cardView2);

                    title = (TextView) view.findViewById(R.id.title_comp2);
                    url1 = (TextView) view.findViewById(R.id.url12);
                    url2 = (TextView) view.findViewById(R.id.url22);

                    url1.setMovementMethod(LinkMovementMethod.getInstance());
                    url2.setMovementMethod(LinkMovementMethod.getInstance());



                    ageTxt.setText(R.string.jrfll_age);
                    teamTxt.setText(R.string.jrfll_team);
                    robotTxt.setText(R.string.jrfll_robot);
                    langProgTxtName.setVisibility(View.INVISIBLE);
                    langProgTxt.setVisibility(View.INVISIBLE);
                    goURL = "http://robofest.ru/sorevnovaniya/JrFLL/";

                  /*  url1.setTextSize(14);
                    url2.setTextSize(0);*/
                    url1.setText(Html.fromHtml(view.getResources().getString(R.string.url1_jrfll)));

                    isLangProg = false;

                    break;
                case 3:
                    ageTxt = (TextView) view.findViewById(R.id.text_age3);
                    teamTxt = (TextView) view.findViewById(R.id.text_team3);
                    robotTxt = (TextView) view.findViewById(R.id.text_robot3);
                    langProgTxt = (TextView) view.findViewById(R.id.text_langProg3);
                    langProgTxtName = (TextView) view.findViewById(R.id.text_langProg_name3);

                    cardView = (CardView) view.findViewById(R.id.cardView3);

                    title = (TextView) view.findViewById(R.id.title_comp3);
                    url1 = (TextView) view.findViewById(R.id.url13);
                    url2 = (TextView) view.findViewById(R.id.url23);

                    url1.setMovementMethod(LinkMovementMethod.getInstance());
                    url2.setMovementMethod(LinkMovementMethod.getInstance());



                    ageTxt.setText(R.string.hr_age);
                    teamTxt.setText(R.string.hr_team);
                    robotTxt.setText(R.string.hr_robot);
                    langProgTxt.setText(R.string.hr_langProg);
                    goURL = "http://robofest.ru/sorevnovaniya/HR/";

                 /*   url1.setTextSize(14);
                    url2.setTextSize(0);*/
                    url1.setText(Html.fromHtml(view.getResources().getString(R.string.url1_hr)));

                    break;
                case 4:
                    ageTxt = (TextView) view.findViewById(R.id.text_age);
                    teamTxt = (TextView) view.findViewById(R.id.text_team);
                    robotTxt = (TextView) view.findViewById(R.id.text_robot);
                    langProgTxt = (TextView) view.findViewById(R.id.text_langProg);
                    langProgTxtName = (TextView) view.findViewById(R.id.text_langProg_name);

                    cardView = (CardView) view.findViewById(R.id.cardView);

                    title = (TextView) view.findViewById(R.id.title_comp);
                    url1 = (TextView) view.findViewById(R.id.url1);
                    url2 = (TextView) view.findViewById(R.id.url2);

                    url1.setMovementMethod(LinkMovementMethod.getInstance());
                    url2.setMovementMethod(LinkMovementMethod.getInstance());


                    ageTxt.setText(R.string.robocarusel_age);
                    teamTxt.setText(R.string.robocarusel_team);
                    robotTxt.setText(R.string.robocarusel_robot);
                    langProgTxt.setText(R.string.robocarusel_langProg);
                    goURL = "http://robofest.ru/sorevnovaniya/robokarusel/";
                  /*  url1.setTextSize(14);
                    url2.setTextSize(14);*/
                    url1.setText(Html.fromHtml(view.getResources().getString(R.string.url1_robocarusel)));
                    url2.setText(Html.fromHtml(view.getResources().getString(R.string.url2_robocarusel)));
                    break;
                default:

                    //   rv.setAdapter(new CompetitionAdapter("HRwrehw;o", getContext()));
                    break;
            }




        }
    }
}
