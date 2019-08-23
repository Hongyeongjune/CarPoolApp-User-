package com.example.user.cardoners;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.cardoners.mqtt.Constants;
import com.example.user.cardoners.mqtt.PahoMqttClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class RequestActivity extends AppCompatActivity {
    static private PahoMqttClient client;
    RequestAdapter adapter;
    private String host="3.16.131.75";
    private String topic="asd";
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

//        Intent intent = getIntent();  //--------------추가
//        String msg = intent.getStringExtra("msg"); //---------------추가

        // Adapter 생성
        adapter = new RequestAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_request);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserinfoActivity.class);
                startActivity(intent);
            }
        });


        Constants.PUBLISH_TOPIC=topic;
        if (!topic.isEmpty()) {
            //                        pahoMqttClient.subscribe(client, topic, 1);
            if (client == null)
                client = new PahoMqttClient( getApplicationContext(),host, topic,1,"subClient",true,null);
                client.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean b, String s) {

                }

                @Override
                public void connectionLost(Throwable throwable) {

                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    Log.e("MQTT","msg:" + new String(mqttMessage.getPayload())   );

                    String msg = new String(mqttMessage.getPayload());

//                    String msg2 = msg.replaceAll("\\{","");  //특수문자제거
//                    String msg3 = msg2.replaceAll("\\}",""); //특수문자제거
                    String[] arr = msg.split(",");
                    Log.d("MQTT","arr[1]:"+arr[1]+", arr[2] : "+arr[2]);
                    if("1".equals(arr[1])) {      //메세지 전문 '1' 일때만 운행내역에 추가
                        adapter.addItem2(arr[0],arr[6],arr[7],"",arr[2],arr[3]);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });


        }


//        // 첫 번째 아이템 추가.
    }
}