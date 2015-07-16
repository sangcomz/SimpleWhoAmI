package kr.co.sangcomz.whoami2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import kr.co.sangcomz.whoami2.fragment.Album;
import kr.co.sangcomz.whoami2.fragment.Free;
import kr.co.sangcomz.whoami2.fragment.Hobby;
import kr.co.sangcomz.whoami2.fragment.Profile;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    MainFragmentAdapter mainFragmentAdapter; //adapter 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);         //툴바 xml 아이디 연걸
        viewPager = (ViewPager) findViewById(R.id.viewpager);   //viewpager xml 아이디 연걸
        tabLayout = (TabLayout) findViewById(R.id.tabs);        //tabLayout xml 아이디 연걸

        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager()); //adapter 객체 생성

        setSupportActionBar(toolbar);   //AppCompatActivity actionbar를 설정

        getSupportActionBar().setTitle("who am i"); //타이틀 설정
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher); //아이콘 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //아이콘 여부

        setUpViewPager(viewPager, mainFragmentAdapter); //viewPager에  adapter를 달아준다.

        tabLayout.setupWithViewPager(viewPager);


    }

    /**
     * viewPager에 adapter를 설정해준다.
     * @param viewPager
     * @param mainFragmentAdapter
     */
    public void setUpViewPager(ViewPager viewPager, MainFragmentAdapter mainFragmentAdapter) {
        mainFragmentAdapter.addFragment(new Profile(), "프로필"); //adapter에 Fragment를 더해준다.
        mainFragmentAdapter.addFragment(new Hobby(), "취미");
        mainFragmentAdapter.addFragment(new Album(), "앨범");
        mainFragmentAdapter.addFragment(new Free(), "자유");
        viewPager.setAdapter(mainFragmentAdapter);
    }

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
}
