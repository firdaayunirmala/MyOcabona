package com.program.myocabona.CategoryTabs;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class CategoryTabAdapter extends FragmentStatePagerAdapter{
    List<String> tabTitle;
    List<Fragment> tabFragments;

    public CategoryTabAdapter(FragmentManager fm, List<String> tabTitle, List<Fragment> tabFragments) {
        super(fm);
        this.tabTitle = tabTitle;
        this.tabFragments = tabFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return tabFragments.get(i);
    }

    @Override
    public int getCount() {
        return tabTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}
