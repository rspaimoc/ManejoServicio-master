package com.android.mdw.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


/**
 * Created by radu on 21/03/18.
 */

public class MusicReceiver extends BroadcastReceiver {

   /* @Override
    public void onCreate() {
        Toast.makeText(this, R.string.creabro, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, R.string.finabro, Toast.LENGTH_LONG).show();

    }*/


    @Override
    public void onReceive(Context context, Intent intent){
        //Toast.makeText(context, "Me ha llegao un kilo de vergas", Toast.LENGTH_LONG).show();
        Toast.makeText(context, R.string.creabro, Toast.LENGTH_LONG).show();
        String m = new String();
        Intent service = new Intent(context, ElServicio.class);
        if(intent.getExtras() != null){
            String msg = intent.getStringExtra("msg");
            service.putExtra("msg", msg);

            if(msg.equals("Inciar Cancion")){

                m = m + context.getResources().getString(R.string.intentreceptor)
                        + context.getResources().getString(R.string.inciorep)
                        + context.getResources().getString(R.string.c);
                Toast.makeText(context, m, Toast.LENGTH_LONG).show();
                context.startService(service);

            } else if (msg.equals("Iniciar Sonido")) {

                m = m + context.getResources().getString(R.string.intentreceptor)
                        + context.getResources().getString(R.string.inciorep)
                        + context.getResources().getString(R.string.s);
                Toast.makeText(context, m, Toast.LENGTH_LONG).show();
                context.startService(service);

            } else {

                m = m + context.getResources().getString(R.string.intentreceptor)
                      + context.getResources().getString(R.string.detrep);
                Toast.makeText(context, m, Toast.LENGTH_LONG).show();
                context.stopService(service);

            }
        }

    }


    /*public void onCreate(Bundle savedInstanceState) {
        Toast.makeText(savedInstanceState, R.string.creaserv, Toast.LENGTH_LONG).show();
    }*/
}
