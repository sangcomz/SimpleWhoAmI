package kr.co.sangcomz.whoami2;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import kr.co.sangcomz.whoami2.adapter.MainFragmentAdapter;
import kr.co.sangcomz.whoami2.fragment.Album;
import kr.co.sangcomz.whoami2.fragment.Hobby;
import kr.co.sangcomz.whoami2.fragment.Portfolio;
import kr.co.sangcomz.whoami2.fragment.Profile;
import kr.co.sangcomz.whoami2.util.AnimUtils;
import kr.co.sangcomz.whoami2.util.Dialogs;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    MainFragmentAdapter mainFragmentAdapter; //Fragment adapter 선언
    AppBarLayout appBarLayout;
    FloatingActionButton mFab; // FloatingActionButton 선언
    int currentPosition = 0; //현재 선택된 페이지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);             //툴바 xml 아이디 연걸
        viewPager = (ViewPager) findViewById(R.id.viewpager);       //viewpager xml 아이디 연걸
        tabLayout = (TabLayout) findViewById(R.id.tabs);            //tabLayout xml 아이디 연걸
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);    //appBarLayout xml 아이디 연걸

        mFab = (FloatingActionButton) findViewById(R.id.fab); //FAB 선언
        //fab 클릭 리스너.
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Dialogs().DialogHobby(MainActivity.this);
            }
        });

        setToolbar();

        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager()); //adapter 객체 생성
        setUpViewPager(viewPager, mainFragmentAdapter); //viewPager에  adapter를 달아준다.
        tabLayout.setupWithViewPager(viewPager);


        //appBarLayout 위치 변경에 따른 리스너.
        //https://developer.android.com/reference/android/support/design/widget/AppBarLayout.OnOffsetChangedListener.html 리스너 설명
        /**
         * 선택된 탭이 취미일때, 앱바가 들어갈땐 액션버튼도 사라지고 앱바가 나타날땐 액션버튼이 나타난다.
         */
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (currentPosition == 1) {
                    if (verticalOffset < 0)
                        animFab(0);
                    else
                        animFab(1);

                }

            }
        });

        /**
         * 선택된 탭이 바뀔때 반응하는 리스너. 취미에서만 액션버튼을 사용하므로 나머지에선 animFab(0)을 통해서 액션버튼을 없애준다.
         */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        animFab(0);
                        currentPosition = 0;
                        viewPager.setCurrentItem(position);
                        getSupportActionBar().setTitle("프로필"); //타이틀 설정
                        break;
                    case 1:
                        animFab(1);
                        currentPosition = 1;
                        viewPager.setCurrentItem(position);
                        getSupportActionBar().setTitle("취미"); //타이틀 설정
                        break;
                    case 2:
                        animFab(0);
                        currentPosition = 2;
                        viewPager.setCurrentItem(position);
                        getSupportActionBar().setTitle("앨범"); //타이틀 설정
                        break;
                    case 3:
                        animFab(0);
                        currentPosition = 3;
                        viewPager.setCurrentItem(position);
                        getSupportActionBar().setTitle("포트폴리오"); //타이틀 설정
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

    private void setToolbar() {
        setSupportActionBar(toolbar);   //AppCompatActivity actionbar를 설정

        getSupportActionBar().setTitle("프로필"); //타이틀 설정
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher); //아이콘 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //아이콘 여부
    }

    /**
     * viewPager에 adapter를 설정해준다.
     */
    public void setUpViewPager(ViewPager viewPager, MainFragmentAdapter mainFragmentAdapter) {
        mainFragmentAdapter.addFragment(new Profile(), "프로필"); //adapter에 Fragment를 더해준다.
        mainFragmentAdapter.addFragment(new Hobby(), "취미");
        mainFragmentAdapter.addFragment(new Album(), "앨범");
        mainFragmentAdapter.addFragment(new Portfolio(), "포트폴리오");
        viewPager.setAdapter(mainFragmentAdapter);
        viewPager.setOffscreenPageLimit(4); //4
    }

    /**
     * 스케일 애니메이션
     *
     * @param scale 0 = 사라짐 1 = 원래 크기
     */
    private void animFab(final float scale) {
        ViewCompat.animate(mFab)
                .setInterpolator(AnimUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
                .scaleX(scale)
                .scaleY(scale)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        if (scale == 1) mFab.setVisibility(View.VISIBLE);
                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        if (scale == 0) mFab.setVisibility(View.GONE);
                    }
                })
                .setDuration(250)   //기간
                .withLayer()        //Software Type Hardware Type
                .start();

    }

}
