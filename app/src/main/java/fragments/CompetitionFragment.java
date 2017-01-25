package fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import adapters.CompetitionAdapter;
import ru.velkonost.robofest.R;

public class CompetitionFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_competition;

    private int competitionId; // по этому ид определять, какое соревнование и парсить уже его

    private String textHistory;
    private String text;


    private Document doc = null, doc2 = null;


    public static CompetitionFragment getInstance(Context context, int competitionId, String title) {
        Bundle args = new Bundle();
        CompetitionFragment fragment = new CompetitionFragment();

        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setCompetitionId(competitionId);
        fragment.setTitle(title);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerViewCompetition);
        rv.setLayoutManager(new LinearLayoutManager(context));

        GetHtml getHtml = new GetHtml();
        getHtml.execute();

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    private class GetHtml extends AsyncTask<Object, Object, String> {
        @Override
        protected String doInBackground(Object... strings) {

            /**
             * Формирование адреса, по которому необходимо обратиться.
             **/
            String dataURL = "http://developer.alexanderklimov.ru/android/";
            String goURL = null;

            switch (competitionId) {
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
                    goURL = "http://robofest.ru/sorevnovaniya/JrFLL/";
                    break;
                default:

                    //   rv.setAdapter(new CompetitionAdapter("HRwrehw;o", getContext()));
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

            return dataURL;
        }
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);


            //List title = doc.select("article[class=box post]").select("p");
            //textHistory = doc.select("div.content").text();
            //text = doc.select("div.content").text();

            List title2 = doc.select("div.content-wrapper").select("div.content").select("p");
            textHistory = "";
            text = "";
            switch (competitionId) {
                case 1:
                    text = TextUtils.join(" ", title2.subList(5, 7));
                    text+="\n";
                    text = "First Lego League (FLL)\n"+text+"\n"+"Ссылки: "+"\n";
                    text+="\nИнформация взята отсюда: \nhttp://robofest.ru/sorevnovaniya/FLL/";
                 /*   Elements links = doc2.select("div.content-wrapper")
                            .select("div.content")
                            .select("div.two-cell")
                            .select("a");
                    for (Element link : links) {
                        // myList.add(link.text());
                        // linksList.add(link.attr("href"));
                        text+=(link.text() + ":"+ "\n" + link.attr("href") + "\n");
                    }
                    // text+= TextUtils.join(" ", title2.subList(4, 7));*/
                    break;
                case 2:
                    text = TextUtils.join(" ", title2.subList(2, 5));
                    text = "Junior First Lego League (Jr.FLL)\n"+text;
                    text+="\n";
                    text+= TextUtils.join(" ", title2.subList(7, 7));
                    text+="\nИнформация взята отсюда: \nhttp://robofest.ru/sorevnovaniya/JrFLL/";
                    break;
                case 3:
                    text = TextUtils.join(" ", title2.subList(1, 3));
                    text = "HELLO, ROBOT!\n"+text;
                    text+="\n";
                    text+= TextUtils.join(" ", title2.subList(3, 8));
                    text+="\n";
                    text+= TextUtils.join(" ", title2.subList(5, 8));
                    text+="\nИнформация взята отсюда: \nhttp://robofest.ru/sorevnovaniya/HR/";

               /*     text+="Ссылки: "+"\n";
                    Elements links2 = doc2.select("div.content-wrapper")
                            .select("div.content")
                            .select("a");
                    for (Element link : links2) {
                        // myList.add(link.text());
                        // linksList.add(link.attr("href"));
                        text+=(link.text() + ":"+ "\n" + link.attr("href") + "\n");
                    }*/
                    break;
                case 4:
                    break;
                default:

                    //   rv.setAdapter(new CompetitionAdapter("HRwrehw;o", getContext()));
                    break;
            }



            //textHistory = TextUtils.join(" ", title.subList(1, 5));
            //text = TextUtils.join(" ", title.subList(1, 8));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textHistory = String.valueOf(Html.fromHtml(textHistory, Html.FROM_HTML_MODE_LEGACY));
                text = String.valueOf(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
            } else {
                textHistory = String.valueOf(Html.fromHtml(textHistory));
                text = String.valueOf(Html.fromHtml(text));
            }

            RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerViewCompetition);
            rv.setLayoutManager(new LinearLayoutManager(context));
            rv.setAdapter(new CompetitionAdapter(competitionId,text, getContext()));




        }
    }
}
