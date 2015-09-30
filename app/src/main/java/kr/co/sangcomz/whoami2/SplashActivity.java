package kr.co.sangcomz.whoami2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class SplashActivity extends ActionBarActivity {

    TextView mainTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mainTxt = (TextView)findViewById(R.id.main_text);

        Handler mHandler = new Handler();
        //http://developer.android.com/reference/android/os/Handler.html  http://javacan.tistory.com/213
        //핸들러에 관한 설명

        //http://kyleslab.tistory.com/41
        //Main Thread와 sub Thread

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                 mainTxt.setText("변하는지?"); //오류
////                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
////                    startActivity(i);
////                    finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();

        //http://developer.android.com/reference/java/lang/Runnable.html 런어블에 관한 api
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                mainTxt.setText("변하는지?"); //잘 작동
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, 2000);
    }
}