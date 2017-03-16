package com.sample.foo.simplewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RemoteViews;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class SimpleWidgetProvider extends AppWidgetProvider {

    private static final String OPEN_DOOR    = "openDoorButton";
    private static final String SETTINGS    = "settingz";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.simple_widget);
        ComponentName watchWidget = new ComponentName(context, SimpleWidgetProvider.class);

        remoteViews.setOnClickPendingIntent(R.id.openDoorButton, getPendingSelfIntent(context, OPEN_DOOR));
        remoteViews.setOnClickPendingIntent(R.id.playSoundButton, getPendingSelfIntent(context, SETTINGS));
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        //RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.simple_widget);
        //openDoor(remoteViews);

        if (OPEN_DOOR.equals(intent.getAction())) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.simple_widget);
            ComponentName watchWidget = new ComponentName(context, SimpleWidgetProvider.class);

            openDoor(remoteViews,context);

            AppWidgetManager.getInstance(context).updateAppWidget(watchWidget, remoteViews);
        }
        else if(SETTINGS.equals(intent.getAction())) {
            Intent bewintent = new Intent(context, MainActivity.class);
            bewintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(bewintent);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    // Turns on a light
    private void openDoor( RemoteViews remoteViews,Context context )
    {
        //String number = String.format("%03d", (new Random().nextInt(900) + 100));
        //remoteViews.setTextViewText(R.id.textView, number);
        //
        LocalVariables localVars = new LocalVariables(context);
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            String seconds = localVars.getSeconds();
            String url = "http://" + localVars.getIp() + ":" + localVars.getPort();
            client.get(url + "/api/door/open/"+seconds, new AsyncHttpResponseHandler(){

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        } catch (Exception e) {
            //remoteViews.setTextViewText(R.id.textView, "F-F-F-FAIL");
        }
    }

    public class MyClientTask extends AsyncTask<String, Void, Void> {

        String dstAddress;
        int dstPort;
        String response;

        MyClientTask(String addr, int port) {
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Socket socket = new Socket(dstAddress, dstPort);

                OutputStream outputStream = socket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                printStream.print(params[0]);

                socket.close();

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

    }
}
