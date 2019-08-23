package com.example.user.cardoners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.cardoners.dbmanager.DBManager;
import com.example.user.cardoners.dbmanager.Driver;
import com.example.user.cardoners.dbmanager.DriverHistory;
import com.example.user.cardoners.mqtt.Constants;
import com.example.user.cardoners.mqtt.PahoMqttClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DrivingActivity extends AppCompatActivity implements View.OnClickListener{

    static private PahoMqttClient client;
    private String host = "3.16.131.75";
    private String topic = "asd";

    String message2;

    Button button1;

    List<Integer> galleryId = new ArrayList<Integer>();
    AdapterViewFlipper avf,avf2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);




        button1 = (Button) findViewById(R.id.btn_finish);

        button1.setOnClickListener(this);

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

                    if ("2".equals(arr[1])) {      //메세지 전문 '1' 일때만 운행내역에 추가
                        Log.d("MQTT","arr[0]:"+arr[0]);
                        message2 = arr[0] + ",3";
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
        }

//        pahoMqttClient = new PahoMqttClient();
//        client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);

        for(int i = 1; i < 3; i++){
            galleryId.add(getResources().getIdentifier("circle"+i,"drawable",this.getPackageName()));
        }

        avf =(AdapterViewFlipper) findViewById(R.id.flipper1);
        avf.setAdapter(new galleryAdapter(this));

        avf2 =(AdapterViewFlipper) findViewById(R.id.flipper2);
        avf2.setAdapter(new galleryAdapter(this));

    }
    @Override
    protected void onResume(){
        super.onResume();
        avf.startFlipping();
        avf2.startFlipping();
    }

    @Override
    public void onClick(View v) {

        Log.d("MQTT","message2 :  " + message2);

        if (!message2.isEmpty()) {
            client = new PahoMqttClient( getApplicationContext(),host, topic,1,"pubClient",false,message2);
//                        client.publishMessage( msg, 1, topic);
        }


        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public class galleryAdapter extends BaseAdapter{
        private final Context mContext;
        LayoutInflater inflater;

        public galleryAdapter(Context c){
            mContext = c;
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount(){
            return galleryId.size();
        }

        public Object getItem(int position){
            return position;
        }
        public long getItemId(int position){
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = inflater.inflate(R.layout.flipper1,parent,false);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.circle1);
            imageView.setImageResource(galleryId.get(position));
            return convertView;
        }
    }
}
