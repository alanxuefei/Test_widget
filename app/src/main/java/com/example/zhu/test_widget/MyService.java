package com.example.zhu.test_widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class MyService extends Service implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private int appWidgetId;


    public MyService() {
        Log.i("myservice", "built");

    }
    public int[] allWidgetIds;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        appWidgetId=allWidgetIds[0];
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

        StringBuilder sb = new StringBuilder();
        sb.append("  Azimuth = ").append(String.valueOf(Azimuth)).append("  Pitch = ").append(String.valueOf(Pitch)).append("  Roll = ").append(String.valueOf(Roll));
        RemoteViews views;
        views = new RemoteViews(this.getApplicationContext().getPackageName(),R.layout.new_app_widget123);
        views.setTextViewText(R.id.appwidget_text, sb.toString());
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());
       
        appWidgetManager.updateAppWidget(appWidgetId, views);
       // stopSelf();
        // Do something with this sensor data.
    }
}
