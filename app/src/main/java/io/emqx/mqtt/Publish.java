package io.emqx.mqtt;

import android.location.Location;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.concurrent.Executor;

public class Publish {

    private String topic;

    private String payload;

    private int qos;

    private boolean retained;

    private boolean control;



    public Publish(String topic,String payload, int qos, boolean retained) {

        this.topic = topic;
        this.payload = payload;
        this.qos = qos;
        this.retained = retained;


    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload= payload;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public boolean isRetained() {
        return retained;
    }

    public void setRetained(boolean retained) {
        this.retained = retained;
    }


}
