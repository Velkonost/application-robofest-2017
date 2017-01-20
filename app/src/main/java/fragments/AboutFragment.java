package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapters.AboutAdapter;
import ru.velkonost.robofest.R;

public class AboutFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_about;

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

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerViewAbout);
        rv.setLayoutManager(new LinearLayoutManager(context));


        switch (competitionId) {
            case 1:
                rv.setAdapter(new AboutAdapter("FLL", getContext()));
                break;
            case 2:
                rv.setAdapter(new AboutAdapter("Jr.FLL", getContext()));
                break;
            case 3:
                rv.setAdapter(new AboutAdapter("HR", getContext()));
                break;
            case 4:
                rv.setAdapter(new AboutAdapter("FS", getContext()));
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
}
