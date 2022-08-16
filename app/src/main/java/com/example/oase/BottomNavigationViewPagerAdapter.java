package com.example.oase;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class BottomNavigationViewPagerAdapter extends FragmentStatePagerAdapter {

    public BottomNavigationViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DashboardFragment();
            case 1:
                return new TaskFragment();
            default:
                return new DashboardFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
