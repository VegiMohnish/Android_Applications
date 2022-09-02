package com.example.broadcastreceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(final Context context,final Intent intent){
        Log.i("MyBroadcastReceiver", "MyAction received!");
    }
}
