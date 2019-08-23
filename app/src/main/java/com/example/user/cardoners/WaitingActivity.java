package com.example.user.cardoners;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class WaitingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//반드시 여기 위치
        setContentView(R.layout.activity_waiting);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                startActivity(new Intent(WaitingActivity.this,RequestActivity.class));
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0,3000);
    }

}