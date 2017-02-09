package ru.velkonost.robofest.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import ru.velkonost.robofest.R;
import ru.velkonost.robofest.adapters.AboutAdapter;
import ru.velkonost.robofest.adapters.AboutContactAdapter;
import ru.velkonost.robofest.adapters.AboutOrganizersAdapter;

import static ru.velkonost.robofest.managers.Initializations.hasConnection;

public class AboutFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_about;

    private int columnId; // по этому ид определять, какое соревнование и парсить уже его

    private String textHistory;
    private String text;


    private Document[] doc = {null};

    public static AboutFragment getInstance(Context context, int columnId, String title) {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();

        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(title);
        fragment.setColumnId(columnId);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);


        if (!hasConnection(context)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Ошибка")
                    .setMessage("Отсутствует интернет-соединение!")
                    .setCancelable(false)
                    .setNegativeButton("Хорошо",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        if (hasConnection(context)) {
            switch (columnId) {
                case 1:
                    GetHtml getHtml = new GetHtml();
                    getHtml.execute();
                    break;
                case 2:
                    RecyclerView rv1 = (RecyclerView) view.findViewById(R.id.recyclerViewAbout);
                    rv1.setLayoutManager(new LinearLayoutManager(context));
                    rv1.setAdapter(new AboutOrganizersAdapter());
                    break;
                case 3:
                    RecyclerView rv2 = (RecyclerView) view.findViewById(R.id.recyclerViewAbout);
                    rv2.setLayoutManager(new LinearLayoutManager(context));
                    rv2.setAdapter(new AboutContactAdapter());
                    break;
                default:

                    break;
            }
        }


        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    private class GetHtml extends AsyncTask<Object, Object, String> {
        @Override
        protected String doInBackground(Object... strings) {

            String dataURL = "http://www.robofestomsk.ru/o-festivale.html";


            try {
                doc[0] = Jsoup.connect(dataURL).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return dataURL;
        }
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            List title = doc[0].select("article[class=box post]").select("p");

            textHistory = TextUtils.join(" ", title.subList(1, 5));
            text = TextUtils.join(" ", title.subList(5, 8));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textHistory = String.valueOf(Html.fromHtml(textHistory, Html.FROM_HTML_MODE_LEGACY));
                text = String.valueOf(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
            } else {
                textHistory = String.valueOf(Html.fromHtml(textHistory));
                text = String.valueOf(Html.fromHtml(text));
            }

            RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerViewAbout);
            rv.setLayoutManager(new LinearLayoutManager(context));
            rv.setAdapter(new AboutAdapter(textHistory, text, getContext()));
        }
    }
}
