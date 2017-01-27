package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapters.CompetitionAdapter;
import adapters.TranslationAdapter;
import ru.velkonost.robofest.R;

/**
 * Created by Andrey on 27.01.2017.
 */
public class TranslationFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_translation;
    private int translationId;
    private String text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerViewTranslation);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new TranslationAdapter(translationId,text, getContext()));


        return view;
    }

    public static TranslationFragment getInstance(Context context, int competitionId, String title) {
        Bundle args = new Bundle();
        TranslationFragment fragment = new TranslationFragment();

        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setCompetitionId(competitionId);
        fragment.setTitle(title);

        return fragment;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setCompetitionId(int translationId) {
        this.translationId = translationId;
    }


}
