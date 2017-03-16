package com.sample.foo.simplewidget;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.NumberPicker;

/**
 * Created by m.van.emmerik on 4-2-2017.
 */

public class LocalVariables {

    private static final String FILENAME = "savefile";
    private SharedPreferences sharedPref;
    //
    private static final String PORT    = "prtwtfz";
    private static final String IP      = "ipomgwtfbbqz";
    private static final String SECONDS = "secondszz";


    public LocalVariables(Context _context)
    {
        sharedPref = _context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }


    void savePort(String port)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PORT,port);
        editor.commit();
    }

    void saveIP(String ip)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(IP,ip);
        editor.commit();
    }

    void saveSeconds(String seconds)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SECONDS,seconds);
        editor.commit();
    }


    /* =================== Returns ==================== */

   String getPort()
   {
       return sharedPref.getString(PORT, "1337");
   }

    String getIp()
    {
        return sharedPref.getString(IP, "http://192.168.43.224");
    }

    String getSeconds()
    {
        return sharedPref.getString(SECONDS, "5");
    }

}
