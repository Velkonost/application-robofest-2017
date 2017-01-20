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
import ru.velkonost.robofest.R;

public class CompetitionFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_competition;

    private int competitionId; // по этому ид определять, какое соревнование и парсить уже его


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


        switch (competitionId) {
            case 1:
                rv.setAdapter(new CompetitionAdapter("HR", getContext()));
                break;
            case 2:
                break;
            default:
                rv.setAdapter(new CompetitionAdapter("HRwrehw;o", getContext()));
                break;
        }


        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }
}
