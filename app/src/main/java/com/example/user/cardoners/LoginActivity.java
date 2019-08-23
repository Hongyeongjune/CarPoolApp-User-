package com.example.user.cardoners;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.cardoners.mqtt.Constants;
import com.example.user.cardoners.mqtt.PahoMqttClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Button button1 = (Button) findViewById(R.id.login_next); //해당 버튼을 지정합니다.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 눌렸을 때
                Intent intent = new Intent(LoginActivity.this, AgreeActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });*/

        Button button2 = (Button) findViewById(R.id.volunteer); //해당 버튼을 지정합니다.
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 눌렸을 때
                Intent intent = new Intent(LoginActivity.this, Agree2Activity.class);
                startActivity(intent); //액티비티 이동
    }
});

        Button button3 = (Button) findViewById(R.id.one_taxi); //해당 버튼을 지정합니다.
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 눌렸을 때
                Intent intent = new Intent(LoginActivity.this, AgreeActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });

        Button button4 = (Button) findViewById(R.id.disable_taxi); //해당 버튼을 지정합니다.
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 눌렸을 때
                Intent intent = new Intent(LoginActivity.this, AgreeActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });


    }
}