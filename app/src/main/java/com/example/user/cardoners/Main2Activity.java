package com.example.user.cardoners;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

//import com.example.user.cardoners.mqtt.Constants;
//import com.example.user.cardoners.mqtt.MqttMessageService;
//import com.example.user.cardoners.mqtt.PahoMqttClient;

import com.example.user.cardoners.dbmanager.DBManager;
import com.example.user.cardoners.dbmanager.Driver;
import com.example.user.cardoners.mqtt.Constants;
import com.example.user.cardoners.mqtt.MqttMessageService;
import com.example.user.cardoners.mqtt.PahoMqttClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static private PahoMqttClient client;
    private String TAG = "MainActivity";
    //    private PahoMqttClient client;
    private String host="3.16.131.75";
    private String topic="asd";

    RequestAdapter adapter;

    ListView listview;

    Driver UserData;

    DBManager dbManager = new DBManager(
            this,
            "register",
            null,1
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button1 = (Button) findViewById(R.id.request_search); //해당 버튼을 지정합니다.

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 눌렸을 때
//                String topic = "asd";
//                Constants.PUBLISH_TOPIC=topic;
//                if (!topic.isEmpty()) {
//                    //                        pahoMqttClient.subscribe(client, topic, 1);
//                    client = new PahoMqttClient( getApplicationContext(),host, topic,1,"subClient",true,null);
//                    client.setCallback(new MqttCallbackExtended() {
//                        @Override
//                        public void connectComplete(boolean b, String s) {
//
//                        }
//
//                        @Override
//                        public void connectionLost(Throwable throwable) {
//
//                        }
//
//                        @Override
//                        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
//                            Log.e("MQTT","msg:" + new String(mqttMessage.getPayload())   );
//
//                            String msg = new String(mqttMessage.getPayload());
//                            String msg2 = msg.replaceAll("\\{","");  //특수문자제거
//                            String msg3 = msg2.replaceAll("\\}",""); //특수문자제거
//                            String[] arr = msg3.split(",");
//                            if(arr[0].equals("1")) {      //메세지 전문 '1' 일때만 운행내역에 추가
//                                Intent intent2 = new Intent(Main2Activity.this, RequestActivity.class);
//                                intent2.putExtra("msg", msg);
//                                startActivity(intent2);
//                            }
//                        }
//
//                        @Override
//                        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
//
//                        }
//                    });
//
//
//                }
                Intent intent3 = new Intent(Main2Activity.this, RequestActivity.class);
                startActivity(intent3);
            }
        });

        Intent intent = new Intent(Main2Activity.this, MqttMessageService.class);
        startService(intent); //액티비티 이동

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
        adapter = new RequestAdapter();

        listview = (ListView) findViewById(R.id.listview1);

        listview.setAdapter(adapter);

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

                    if ("4".equals(arr[1])) {
                        adapter.addItem2(arr[0],arr[6],arr[7],"",arr[2],arr[3]);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
        }
        */

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.activity_volunteer2) {

                Intent intent = new Intent(Main2Activity.this, Volunteer2Activity.class);
                startActivity(intent); //액티비티 이동


        } else if (id == R.id.activity_history) {
            Intent intent = new Intent(Main2Activity.this, HistoryActivity.class);
            startActivity(intent); //액티비티 이동

        } else if (id == R.id.activity_notice) {
            Intent intent = new Intent(Main2Activity.this, NoticeActivity.class);
            startActivity(intent); //액티비티 이동

        } else if (id == R.id.activity_ask) {
            Intent intent = new Intent(Main2Activity.this, AskActivity.class);
            startActivity(intent); //액티비티 이동

        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}