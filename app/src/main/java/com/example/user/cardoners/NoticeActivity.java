package com.example.user.cardoners;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        ListView listview;
        ListViewAdapter2 adapter;

        adapter = new ListViewAdapter2();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview2);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("석가탄신일(5/22) 정산일 변경 안내", "2018.05.14");
        adapter.addItem("서비스 오류 사과드립니다.", "2018.04.30");
        adapter.addItem("드라이버 앱이 새로워졌습니다.", "2018.04.27");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");
        adapter.addItem("안내 메일 오발송 사과드립니다.", "2018.04.23");


    }
}
