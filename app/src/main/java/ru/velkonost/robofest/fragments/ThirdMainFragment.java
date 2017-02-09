package ru.velkonost.robofest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import ru.velkonost.robofest.R;

public class ThirdMainFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_third_main;



    public static ThirdMainFragment getInstance(Context context, String title) {
        Bundle args = new Bundle();
        ThirdMainFragment fragment = new ThirdMainFragment();

        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(title);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        WebView browser=(WebView) view.findViewById(R.id.webMap); //if you gave the id as browser
        browser.getSettings().setJavaScriptEnabled(true); //Yes you have to do it
        browser.loadUrl("http://widgets.2gis.com/widget?type=firmsonmap&options=%7B%22pos%22%3A%7B%22lat%22%3A54.979793274814845%2C%22lon%22%3A73.32429885864259%2C%22zoom%22%3A16%7D%2C%22opt%22%3A%7B%22city%22%3A%22omsk%22%7D%2C%22org%22%3A%22282003257923353%22%7D");

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
