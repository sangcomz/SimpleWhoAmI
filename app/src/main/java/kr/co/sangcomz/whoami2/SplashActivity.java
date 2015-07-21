package kr.co.sangcomz.whoami2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler mHandler = new Handler();
        //http://developer.android.com/reference/android/os/Handler.html  http://javacan.tistory.com/213
        //핸들러에 관한 설명

        //http://developer.android.com/reference/java/lang/Runnable.html 런어블에 관한 api
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, 2000);
    }
}
