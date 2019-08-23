package com.example.user.cardoners;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RequestAdapter extends BaseAdapter {
    private ArrayList<RequestitemActivity> listViewItemList = new ArrayList<RequestitemActivity>() ;

    public RequestAdapter(){

    }
    @Override
    public int getCount(){
        return listViewItemList.size();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_request_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득

        TextView distanceTextView = (TextView) convertView.findViewById(R.id.distance) ;
        TextView timeTextView = (TextView) convertView.findViewById(R.id.time) ;
        TextView ampmTextView = (TextView) convertView.findViewById(R.id.ampm) ;
        TextView start_addressTextView = (TextView) convertView.findViewById(R.id.start_address) ;
        TextView end_addressTextView = (TextView) convertView.findViewById(R.id.end_address) ;






        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        RequestitemActivity listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        distanceTextView.setText(listViewItem.getDistance());
        timeTextView.setText(listViewItem.getTime());
        ampmTextView.setText(listViewItem.getAmpm());
        start_addressTextView.setText(listViewItem.getStart_address());
        end_addressTextView.setText(listViewItem.getEnd_address());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
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
    /*public void addItem(String id, String name, String phone) {
        boolean found = false;

        for(int i=0; i<this.getCount(); i++) {
            RequestitemActivity itm = (RequestitemActivity) this.getItem(i);

            if(itm.getId().equals(id)) {
                itm.setId(id);
                itm.setName(name);
                itm.setPhone(phone);
                found = true;
            }
        }

        if(!found) {
            RequestitemActivity item = new RequestitemActivity();
            item.setId(id);
            item.setName(name);
            item.setPhone(phone);
            listViewItemList.add(item);
        }


    }
*/
    public void addItem2(String id, String distance, String time, String ampm, String start_address, String end_address) {
        boolean found = false;
        for (int i = 0; i < this.getCount(); i++) {
            RequestitemActivity itm = (RequestitemActivity) this.getItem(i);
            if (itm.getId().equals(id)) {
                itm.setId(id);
                itm.setDistance(distance);
                itm.setTime(time);
                itm.setAmpm(ampm);
                itm.setStart_address(start_address);
                itm.setEnd_address(end_address);
                found=true;
//                listViewItemList.set(i,itm);//???????
            }
        }
        if (!found) {
            RequestitemActivity item = new RequestitemActivity();
            item.setId(id);
            item.setDistance(distance);
            item.setTime(time);
            item.setAmpm(ampm);
            item.setStart_address(start_address);
            item.setEnd_address(end_address);
            listViewItemList.add(item);
        }

    }


}