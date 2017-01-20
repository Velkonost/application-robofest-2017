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

import adapters.AboutAdapter;
import ru.velkonost.robofest.R;

public class AboutFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_about;

    private int competitionId; // по этому ид определять, какое соревнование и парсить уже его

    String text;

    private Document[] doc = {null};

    public static AboutFragment getInstance(Context context, int competitionId, String title) {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();

        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(title);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);




        switch (competitionId) {
            case 1:

//                rv.setAdapter(new AboutAdapter("FLL", getContext()));
                break;
            case 2:
//                rv.setAdapter(new AboutAdapter("Jr.FLL", getContext()));
                break;
            case 3:
//                rv.setAdapter(new AboutAdapter("HR", getContext()));
                break;
            default:
                GetHtml getHtml = new GetHtml();
                getHtml.execute();
                break;
        }


        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setAboutId(int competitionId) {
        this.competitionId = competitionId;
    }

    private class GetHtml extends AsyncTask<Object, Object, String> {
        @Override
        protected String doInBackground(Object... strings) {

            /**
             * Формирование адреса, по которому необходимо обратиться.
             **/
            String dataURL = "http://developer.alexanderklimov.ru/android/";

            /**
             * Формирование отправных данных.
             */

            try {
                doc[0] = Jsoup.connect("http://www.robofestomsk.ru/o-festivale.html").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return dataURL;
        }
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);


            List title = doc[0].select("article[class=box post]").select("p").subList(1, 5);

//            text = title.toString();

            text = TextUtils.join(" ", title);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                text = String.valueOf(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
            else
                text = String.valueOf(Html.fromHtml(text));

            RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerViewAbout);
            rv.setLayoutManager(new LinearLayoutManager(context));
            rv.setAdapter(new AboutAdapter(text, getContext()));




        }
    }
}
