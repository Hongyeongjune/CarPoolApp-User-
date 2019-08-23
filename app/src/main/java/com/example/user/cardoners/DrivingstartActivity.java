package com.example.user.cardoners;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.user.cardoners.dbmanager.DBManager;
import com.example.user.cardoners.dbmanager.Driver;
import com.example.user.cardoners.mqtt.Constants;
import com.example.user.cardoners.mqtt.PahoMqttClient;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class DrivingstartActivity extends Activity implements OnClickListener{

    static private PahoMqttClient client;
    private String host = "3.16.131.75";
    private String topic = "asd";
    String message;

    Driver UserData;

    DBManager dbManager = new DBManager(
            this,
            "register",
            null,1
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//반드시 여기 위치
        setContentView(R.layout.activity_drivingstart);

        UserData =  dbManager.getDriverData();

        Button button1 = (Button) findViewById(R.id.btn_yes);
        Button button2 = (Button) findViewById(R.id.btn_no);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

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

                        message = arr[0] + ",2," + UserData.getName() + "," + UserData.getPhone() + "," + UserData.getCarNumber() + "," + UserData.getCarType() + "," + UserData.getLicenseFinish();


                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
        }

/*
        button1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),DrivingActivity.class);

                        startActivity(intent);
                    }

                }
        );





        button2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),UserinfoActivity.class);

                        startActivity(intent);
                    }

                }
        );
*/

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                if (!message.isEmpty()) {
                    client = new PahoMqttClient( getApplicationContext(),host, topic,1,"pubClient",false,message);
//                        client.publishMessage( msg, 1, topic);
                }
                startActivity(new Intent(this, DrivingActivity.class));
                break;
            case R.id.btn_no:
                this.finish();
                break;
            default:
                break;
        }


    }
}