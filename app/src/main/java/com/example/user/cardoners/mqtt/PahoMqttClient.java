package com.example.user.cardoners.mqtt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

/**
 * Created by brijesh on 20/4/17.
 */

public class PahoMqttClient {

    private static final String TAG = "PahoMqttClient";
    public boolean forSub;
    private MqttAndroidClient  mqttAndroidClient;
    public String host;
    public String topic;
    public int qos;
    public String initMsg;
    public String subscribed="";
    public PahoMqttClient(Context context, String brokerUrl,String topic,int qos, String clientId, boolean forSub, String msg){
        this.host=brokerUrl;
        this.topic=topic;
        this.qos=qos;
        this.forSub=forSub;
        this.initMsg=msg;
        brokerUrl="tcp://"+brokerUrl+":1883";
        Log.i(" ### ","url:"+brokerUrl+" clientId:"+clientId);
        this.connectMqttClient(context, brokerUrl, clientId);
    }

    public void setCallback(MqttCallbackExtended callbackExtended) {
        this.mqttAndroidClient.setCallback(callbackExtended);
    }
    public MqttAndroidClient connectMqttClient(Context context, String brokerUrl, String clientId) {
        if (this.mqttAndroidClient==null) {
            this.mqttAndroidClient = new MqttAndroidClient(context, brokerUrl, clientId);
            Log.d(" #### ", "mqttAndroidClient created !"+String.valueOf(mqttAndroidClient));
        }
        connect();
        return this.mqttAndroidClient;
    }

    public void connect() {
        try {
            IMqttToken token = mqttAndroidClient.connect(getMqttConnectionOption());
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    mqttAndroidClient.setBufferOpts(getDisconnectedBufferOptions());
                    Log.d(TAG, "##### Success ########@@@@@@@");
                    if (forSub) {
                        if (subscribed.equals(topic))
                            return;
                        try {
                            subscribe(topic, qos);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            publishMessage( initMsg, qos, topic);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "Failure " + exception.toString());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void reconnect() throws MqttException {
        IMqttToken mqttToken = this.mqttAndroidClient.disconnect();
        mqttToken.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d(TAG, "Successfully disconnected");
                subscribed="";
                connect();
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                Log.d(TAG, "Failed to disconnected " + throwable.toString());
            }
        });
    }

    public void disconnect() throws MqttException {
        IMqttToken mqttToken = this.mqttAndroidClient.disconnect();
        mqttToken.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d(TAG, "Successfully disconnected");
                subscribed="";
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                Log.d(TAG, "Failed to disconnected " + throwable.toString());
            }
        });
    }

    @NonNull
    private DisconnectedBufferOptions getDisconnectedBufferOptions() {
        DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
        disconnectedBufferOptions.setBufferEnabled(true);
        disconnectedBufferOptions.setBufferSize(100);
        disconnectedBufferOptions.setPersistBuffer(false);
        disconnectedBufferOptions.setDeleteOldestMessages(false);
        return disconnectedBufferOptions;
    }

    @NonNull
    private MqttConnectOptions getMqttConnectionOption() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setAutomaticReconnect(true);
        //mqttConnectOptions.setWill(Constants.PUBLISH_TOPIC, "I am going offline".getBytes(), 1, true);
        //mqttConnectOptions.setUserName("ngbllzzy");
        //mqttConnectOptions.setPassword("WtjhZKl3OPoK".toCharArray());
        return mqttConnectOptions;
    }


    public void publishMessage(@NonNull String msg, int qos, @NonNull String topic)
            throws MqttException, UnsupportedEncodingException {
        byte[] encodedPayload = new byte[0];
        encodedPayload = msg.getBytes("UTF-8");
        MqttMessage message = new MqttMessage(encodedPayload);
        message.setId(320);
        message.setRetained(true);
        message.setQos(qos);
        this.mqttAndroidClient.publish(topic, message);
    }

    public void subscribe(@NonNull final String t, int qos) throws MqttException {

        IMqttToken token = this.mqttAndroidClient.subscribe(t, qos);
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d(TAG, "Subscribe Successfully " + t);
                subscribed=t;
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                Log.e(TAG, "Subscribe Failed " + t);

            }
        });
    }

    public void unSubscribe(@NonNull final String t) throws MqttException {

        IMqttToken token = mqttAndroidClient.unsubscribe(t);
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d(TAG, "UnSubscribe Successfully " + t);
                subscribed="";
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                Log.e(TAG, "UnSubscribe Failed " + t);
            }
        });
    }

}
