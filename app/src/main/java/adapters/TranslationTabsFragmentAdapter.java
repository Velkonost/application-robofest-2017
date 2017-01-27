package adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import fragments.AbstractTabFragment;
import fragments.CompetitionFragment;
import fragments.TranslationFragment;


public class TranslationTabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TranslationTabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);

        this.context = context;
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();


        tabs.put(0, TranslationFragment.getInstance(context, 1, "Текущие"));
        tabs.put(1, TranslationFragment.getInstance(context, 2, "Завершенные"));

    }
}
