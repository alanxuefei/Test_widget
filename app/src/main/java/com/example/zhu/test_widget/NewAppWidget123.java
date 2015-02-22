package com.example.zhu.test_widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget123 extends AppWidgetProvider {


    static private  final String ACTION_CLICK = "ACTION_CLICK";
    static private  int lock=0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.i("myservice", "Called");
        // Build the intent to call the service


        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i],appWidgetIds);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId,int[] appWidgetIds) {


        RemoteViews views;
        views = new RemoteViews(context.getPackageName(),R.layout.new_app_widget123);

        // Register an onClickListener
        Intent intent = new Intent(context, NewAppWidget123.class);

        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.button, pendingIntent);
        Log.d("stuff", "setonclickpendingintent created");
        // Instruct the widget manager to update the widget
         appWidgetManager.updateAppWidget(appWidgetId, views);

        if (lock == 0) {
            Intent Myservice_intent = new Intent(context.getApplicationContext(), MyService.class);
            Myservice_intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            // Update the widgets via the service
            context.startService(Myservice_intent); lock=1;
        }
        else{
            Intent Myservice_intent = new Intent(context.getApplicationContext(), MyService.class);
            Myservice_intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            // Update the widgets via the service
            context.stopService(Myservice_intent);  lock=0;
        }

    }


}

/*
Intent intent = new Intent(context.getApplicationContext(),
        MyService.class);
intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        // Update the widgets via the service
        context.startService(intent);*/