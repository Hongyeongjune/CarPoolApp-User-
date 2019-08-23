package com.example.user.cardoners;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cardoners.mqtt.Constants;
import com.example.user.cardoners.mqtt.PahoMqttClient;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class UserinfoActivity extends AppCompatActivity {

    static private PahoMqttClient client;
    private String host = "3.16.131.75";
    private String topic = "asd";
    TextView textView1, textView2;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        textView1 = (TextView) findViewById(R.id.user_name);
        textView2 = (TextView) findViewById(R.id.user_phone);

        Button button1 = (Button) findViewById(R.id.btn_driving);
        Button button2 = (Button) findViewById(R.id.btn_call);


        button1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), DrivingstartActivity.class);

                        startActivity(intent);
                    }

                }
        );


        button2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String phone2 = textView2.getText().toString().trim();
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phone2)));
                    }

                }
        );

        Constants.PUBLISH_TOPIC = topic;
        if (!topic.isEmpty()) {
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


                    String msg = new String(mqttMessage.getPayload());

//                    String msg2 = msg.replaceAll("\\{","");  //특수문자제거
//                    String msg3 = msg2.replaceAll("\\}",""); //특수문자제거
                    String[] arr = msg.split(",");

                    if ("1".equals(arr[1])) {      //메세지 전문 '1' 일때만 운행내역에 추가
                        textView1.setText("" + arr[4]);
                        textView2.setText("" + arr[0]);
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
        }
    }
}