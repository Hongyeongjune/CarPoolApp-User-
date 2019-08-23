package com.example.user.cardoners;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListView;

public class MoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        ListView listview;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        //adapter.addItem("11.2", "4:20", "오후", "동작구 상도동", "광진구 화양동", "홍영준", "010-6479-4834");
        //adapter.addItem("7.0", "8:30", "오후", "서대문구 합동", "서초구 서초동", "유수빈", "010-2864-7220");
        //adapter.addItem("17.1", "5:10", "오후", "인천시 부평동", "부천시 소사본동", "조가은", "010-8367-2355");

    }
}


