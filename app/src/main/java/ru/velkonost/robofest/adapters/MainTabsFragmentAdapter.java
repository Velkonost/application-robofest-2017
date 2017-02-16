package ru.velkonost.robofest.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import ru.velkonost.robofest.fragments.AbstractTabFragment;
import ru.velkonost.robofest.fragments.FirstMainFragment;
import ru.velkonost.robofest.fragments.SecondMainFragment;
import ru.velkonost.robofest.fragments.ThirdMainFragment;

public class MainTabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public MainTabsFragmentAdapter(Context context, FragmentManager fm) {
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

        tabs.put(0, FirstMainFragment.getInstance(context, "Карта мероприятий"));
        tabs.put(1, SecondMainFragment.getInstance(context, "Программа мероприятий"));
        tabs.put(2, ThirdMainFragment.getInstance(context,"Схема проезда"));

    }
}
