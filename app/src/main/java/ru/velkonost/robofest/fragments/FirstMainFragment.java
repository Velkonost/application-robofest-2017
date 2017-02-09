package ru.velkonost.robofest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.velkonost.robofest.R;

public class FirstMainFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_first_main;



    public static FirstMainFragment getInstance(Context context, String title) {
        Bundle args = new Bundle();
        FirstMainFragment fragment = new FirstMainFragment();

        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(title);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
