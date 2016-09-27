package kr.co.sangcomz.whoami2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangcomz on 28/09/2016.
 */

//http://blog.daum.net/mailss/19 FragmentPagerAdapter 설명
public class MainFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment); //받은 프레그먼트를 리스트에 더해준다.
        mFragmentTitles.add(title);//받은 String을 리스트에 더해준다.
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
