package com.sample.foo.simplewidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RemoteViews;

/**
 * Created by m.van.emmerik on 4-2-2017.
 */

public class MainActivity extends Activity {

    DrawerLayout mDrawer = null;
    LinearLayout frameLayout = null;
    private static final String SAVE    = "save";
    LocalVariables localvarbs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localvarbs = new LocalVariables(this);

        setContentView(R.layout.settings);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        frameLayout = (LinearLayout)  findViewById(R.id.drawer_left);
        // mDrawer.openDrawer(frameLayout);
        //
        prepare();
        Button butt = (Button) findViewById(R.id.save);
        butt.setOnClickListener(btnClick());
    }

    View.OnClickListener btnClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        };
    }

    void save()
    {
        EditText port = (EditText) findViewById(R.id.port);
        EditText ip = (EditText) findViewById(R.id.IP);
        EditText seconds = (EditText) findViewById(R.id.seconds);
        //
        localvarbs.saveIP(ip.getText().toString());
        localvarbs.savePort(port.getText().toString());
        localvarbs.saveSeconds(seconds.getText().toString());
    }

    void prepare()
    {
        EditText port = (EditText) findViewById(R.id.port);
        EditText ip = (EditText) findViewById(R.id.IP);
        EditText seconds = (EditText) findViewById(R.id.seconds);
        //
        port.setText(localvarbs.getPort());
        ip.setText(localvarbs.getIp());
        seconds.setText(localvarbs.getSeconds());
    }


}


