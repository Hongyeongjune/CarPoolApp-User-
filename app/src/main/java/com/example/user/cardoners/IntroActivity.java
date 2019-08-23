package com.example.user.cardoners;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.app.Activity;
import android.content.*;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cardoners.dbmanager.DBManager;

public class IntroActivity extends Activity {

    Handler h;//핸들러 선언

    DBManager dbManager;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //인트로화면이므로 타이틀바를 없앤다
        setContentView(R.layout.activity_intro);
        h= new Handler(); //딜래이를 주기 위해 핸들러 생성
        h.postDelayed(mrun, 2000); // 딜레이 ( 런어블 객체는 mrun, 시간 2초)
    }

    Handler handler = new Handler();
    Runnable mrun = new Runnable(){
        @Override
        public void run(){

            dbManager = new DBManager(
                    IntroActivity.this,
                    "register",
                    null,1
            );

            database = dbManager.getWritableDatabase();
            sql = "SELECT * FROM TEST_TABLE ";
            cursor = database.rawQuery(sql,null);

            if(cursor.getCount() == 0) {

                Intent i = new Intent(getApplicationContext(), VolunteerActivity.class); //인텐트 생성(현 액티비티, 새로 실행할 액티비티)
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                finish();
            }

            else {

                Intent i = new Intent(getApplicationContext(), Main2Activity.class); //인텐트 생성(현 액티비티, 새로 실행할 액티비티)
                startActivity(i);
                finish();
            }
            /*
            Intent i = new Intent(IntroActivity.this, LoginActivity.class); //인텐트 생성(현 액티비티, 새로 실행할 액티비티)
            startActivity(i);
            finish();
            */
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //overridePendingTransition 이란 함수를 이용하여 fade in,out 효과를줌. 순서가 중요
        }
    };
    //인트로 중에 뒤로가기를 누를 경우 핸들러를 끊어버려 아무일 없게 만드는 부분
    //미 설정시 인트로 중 뒤로가기를 누르면 인트로 후에 홈화면이 나옴.

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        h.removeCallbacks(mrun);
    }
    @Override
    protected void onResume() {
        super.onResume();
// 다시 화면에 들어어왔을 때 예약 걸어주기
        handler.postDelayed(mrun, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
// 화면을 벗어나면, handler 에 예약해놓은 작업을 취소하자
        handler.removeCallbacks(mrun); // 예약 취소
    }
}