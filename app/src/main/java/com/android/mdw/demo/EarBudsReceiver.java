package com.android.mdw.demo;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;



/**
 * Created by radu on 21/03/18.
 */

public class EarBudsReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent){
        Intent service = new Intent(context, MusicReceiver.class);

        if(intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)){
            int state = intent.getIntExtra("state", 0);
            switch (state) {
                case 0:
                    Toast.makeText(context, "Intent recibido El Receptor - EVENTO DEL SISTEMA-OFF", Toast.LENGTH_LONG).show();
                    service.putExtra("msg", "Seleccionado Detener");
                    context.stopService(service);
                    /*if (isPlaying()){
                        stopStreaming();
                    }*/
                    break;
                case 1:
                    Toast.makeText(context, "Intent recibido El Receptor - EVENTO DEL SISTEMA-ON", Toast.LENGTH_LONG).show();
                    service.putExtra("msg", intent.getStringExtra("msg"));
                    context.startService(service);
                    break;
                default:
                    //No va a llegar nunca
                    break;
            }
        }

    }

    /*public void onPause() {
        super.onPause();

        unregisterReceiver(this);
    }*/
}
