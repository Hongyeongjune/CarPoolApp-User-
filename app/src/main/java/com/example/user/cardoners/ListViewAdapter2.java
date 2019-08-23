package com.example.user.cardoners;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter2 extends BaseAdapter {
    private ArrayList<ListViewItem2> listViewItemList = new ArrayList<ListViewItem2>();

    ListViewAdapter2() {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item2, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
        TextView dayTextView = (TextView) convertView.findViewById(R.id.day);

        ListViewItem2 listViewItem = listViewItemList.get(position);

        titleTextView.setText(listViewItem.getTitleStr());
        dayTextView.setText(listViewItem.getDayStr());

        return convertView;
    }
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title, String day) {
        ListViewItem2 item = new ListViewItem2();

        item.setTitleStr(title);
        item.setDayStr(day);

        listViewItemList.add(item);
    }
}
