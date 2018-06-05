package com.ezi.larbianceur.esigym.suppliment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SupplimentsPageAdapter extends FragmentPagerAdapter

    {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public SupplimentsPageAdapter( FragmentManager fm) {
        super(fm);
    }


        @Override
        public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

        @Override
        public int getCount() {
        return mFragmentList.size();
    }
}
