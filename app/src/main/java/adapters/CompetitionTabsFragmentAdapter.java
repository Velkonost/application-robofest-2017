package adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import fragments.AbstractTabFragment;
import fragments.CompetitionFragment;


public class CompetitionTabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public CompetitionTabsFragmentAdapter(Context context, FragmentManager fm) {
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


        tabs.put(0, CompetitionFragment.getInstance(context, 1, "FLL"));
        tabs.put(1, CompetitionFragment.getInstance(context, 2, "Jr.FLL"));
        tabs.put(2, CompetitionFragment.getInstance(context, 3, "HR"));
        tabs.put(3, CompetitionFragment.getInstance(context, 4, "РОБОКАРУСЕЛЬ"));

    }
}
