package kr.co.sangcomz.whoami2;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    MainFragmentAdapter mainFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("who am i");
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViewPager(viewPager, mainFragmentAdapter);

        tabLayout.setupWithViewPager(viewPager);


    }

    public void setUpViewPager(ViewPager viewPager, MainFragmentAdapter mainFragmentAdapter) {
        mainFragmentAdapter.addFragment(new BlankFragment(), "aaa");
        mainFragmentAdapter.addFragment(new BlankFragment(), "bbb");
        mainFragmentAdapter.addFragment(new BlankFragment(), "ccc");
        mainFragmentAdapter.addFragment(new BlankFragment(), "ddd");
        viewPager.setAdapter(mainFragmentAdapter);
    }

    public class MainFragmentAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
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
}
