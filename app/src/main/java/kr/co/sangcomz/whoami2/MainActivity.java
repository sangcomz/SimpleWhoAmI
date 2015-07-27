package kr.co.sangcomz.whoami2;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kr.co.sangcomz.whoami2.fragment.Album;
import kr.co.sangcomz.whoami2.fragment.Free;
import kr.co.sangcomz.whoami2.fragment.Hobby;
import kr.co.sangcomz.whoami2.fragment.Profile;
import kr.co.sangcomz.whoami2.util.AnimUtils;
import kr.co.sangcomz.whoami2.util.Dialogs;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    AppBarLayout appBarLayout;

    FloatingActionButton mFab; // FloatingActionButton 선언

    MainFragmentAdapter mainFragmentAdapter; //Fragment adapter 선언
    int currentPosition = 0; //현재 선택된 페이지

    public static ArrayList<String> hobbys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hobbys = new ArrayList<String>();

        toolbar = (Toolbar) findViewById(R.id.toolbar);             //툴바 xml 아이디 연걸
        viewPager = (ViewPager) findViewById(R.id.viewpager);       //viewpager xml 아이디 연걸
        tabLayout = (TabLayout) findViewById(R.id.tabs);            //tabLayout xml 아이디 연걸
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);    //appBarLayout xml 아이디 연걸

        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager()); //adapter 객체 생성

        setSupportActionBar(toolbar);   //AppCompatActivity actionbar를 설정

        getSupportActionBar().setTitle("who am i"); //타이틀 설정
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher); //아이콘 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //아이콘 여부

        setUpViewPager(viewPager, mainFragmentAdapter); //viewPager에  adapter를 달아준다.

        tabLayout.setupWithViewPager(viewPager);

        mFab = (FloatingActionButton) findViewById(R.id.fab); //FAB 선언

        //fab 클릭 리스너.
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Dialogs().DialogHobby(MainActivity.this);
            }
        });

        //appBarLayout 위치 변경에 따른 리스너.
        //https://developer.android.com/reference/android/support/design/widget/AppBarLayout.OnOffsetChangedListener.html 리스너 설명
        /**
         * 선택된 탭이 취미일때, 앱바가 들어갈땐 액션버튼도 사라지도 앱바가 나타날땐 액션버튼이 나타난다.
         */
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (currentPosition == 1) {
                    if (verticalOffset < 0) {
                        animFab(0);
                    } else {
                        animFab(1);
                    }
                }

            }
        });


        /**
         * 선택된 탭이 바뀔때 반응하는 리스너. 취미에서만 액션버튼을 사용하므로 나머지에선 animFab(0)을 통해서 액션버튼을 없애준다.
         */
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mFab.setVisibility(View.VISIBLE);
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        animFab(0);
                        currentPosition = 0;
                        viewPager.setCurrentItem(position);
                        break;
                    case 1:
                        animFab(1);
                        currentPosition = 1;
                        viewPager.setCurrentItem(position);
                        break;
                    case 2:
                        animFab(0);
                        currentPosition = 2;
                        viewPager.setCurrentItem(position);
                        break;
                    case 3:
                        animFab(0);
                        currentPosition = 3;
                        viewPager.setCurrentItem(position);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    /**
     * viewPager에 adapter를 설정해준다.
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

    /**
     * 스케일 애니메이션
     *
     * @param scale 0 = 사라짐 1 = 원래 크기
     */
    private void animFab(final float scale) {
        ViewCompat.animate(mFab)
//                .setInterpolator(AnimUtils.FAST_OUT_SLOW_IN_INTERPOLATOR) //사라지는 모양
                .setInterpolator(AnimUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
//                .setInterpolator(AnimUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR)
                .scaleX(scale)      //x축 스케일
                .scaleY(scale)      //y축 스케일
                .withStartAction(new Runnable() {
                    @Override
                    public void run() { //애니메이션 시작과 함께 해줄 액션
                        if (scale == 1)
                            mFab.setVisibility(View.VISIBLE);
                    }
                })
                .withEndAction(new Runnable() { //애니메이션 종료시 해줄 액션
                    @Override
                    public void run() {
                        if (scale == 0)
                            mFab.setVisibility(View.GONE);
                    }
                })
                .setDuration(250)   //기간
                .withLayer()        //????
                .start();
    }
}
