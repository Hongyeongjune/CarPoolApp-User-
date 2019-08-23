package com.example.user.cardoners;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListView;

import com.example.user.cardoners.dbmanager.DBManager;
import com.example.user.cardoners.mqtt.Constants;
import com.example.user.cardoners.mqtt.PahoMqttClient;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    static private PahoMqttClient client;
    private DBManager dbManager;
    RequestAdapter adapter;
    private String host="3.16.131.75";
    private String topic="asd";
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbManager = new DBManager(
                this,
                "DriverHistory",
                null,1
        );



        adapter = new RequestAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        Constants.PUBLISH_TOPIC=topic;
        if (!topic.isEmpty()) {
            //                        pahoMqttClient.subscribe(client, topic, 1);
            if (client == null)
                client = new PahoMqttClient(getApplicationContext(), host, topic, 1, "subClient", true, null);
            client.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean b, String s) {

                }

                @Override
                public void connectionLost(Throwable throwable) {

                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    Log.e("MQTT", "msg:" + new String(mqttMessage.getPayload()));

                    String msg = new String(mqttMessage.getPayload());

//                    String msg2 = msg.replaceAll("\\{","");  //특수문자제거
//                    String msg3 = msg2.replaceAll("\\}",""); //특수문자제거
                    String[] arr = msg.split(",");
                    Log.d("MQTT", "arr[1]:" + arr[1] + ", arr[2] : " + arr[2]);

                        adapter.addItem2(arr[0], arr[6], arr[7], "", arr[2], arr[3]);
                        adapter.notifyDataSetChanged();

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
        }
    }
}


