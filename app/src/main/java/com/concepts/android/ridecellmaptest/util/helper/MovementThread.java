package com.concepts.android.ridecellmaptest.util.helper;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import android.os.Handler;

/**
 * Created by Siris on 3/13/2017.
 */

public class MovementThread extends Thread {

    private List<LatLng> latLngList;
    private Handler handler;
    private String speed;
    private Message message;
    private volatile boolean exit = false;

    public MovementThread(List<LatLng> latLngList, Handler handler, String speed) {
        this.latLngList = latLngList;
        this.handler = handler;
        this.speed = speed;
    }

    @Override
    public void run() {
        float speed = Float.parseFloat(this.speed);
        for (LatLng latLng : latLngList) {
            if(exit) break;
            message = new Message();
            try {
                Thread.sleep((long) (10000 / speed));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bundle b = new Bundle();
            b.putParcelable("LATLNG", latLng);
            message.setData(b);
            handler.sendMessage(message);
        }
    }

    public void stopProcess(){
        exit = true;
    }
}
