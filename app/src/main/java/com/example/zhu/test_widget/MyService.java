package com.example.zhu.test_widget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mProximity;


    public MyService() {
        Log.i("myservice", "built");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);

        return START_STICKY;
    }

    public void  onDestroy(){
        Log.i("myservice", "removed");
        mSensorManager.unregisterListener(this);
       // mSensorManager.unregisterListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float Azimuth = event.values[0];
        float Pitch  = event.values[1];
        float Roll = event.values[2];
        Log.i("Azimuth = ", String.valueOf(Azimuth));
        Log.i("Pitch = ", String.valueOf(Pitch));
        Log.i("Roll = ", String.valueOf(Roll));

       // stopSelf();
        // Do something with this sensor data.
    }
}
