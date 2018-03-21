package com.android.mdw.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    IntentFilter receiver_filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    EarBudsReceiver receiver = new EarBudsReceiver();
    registerReceiver(receiver, receiver_filter);


    Button btnCancion = (Button) findViewById(R.id.btnCancion);
    Button btnSonido = (Button) findViewById(R.id.btnSonido);
    Button btnFin = (Button) findViewById(R.id.btnFin);

    btnCancion.setOnClickListener(this);
    btnSonido.setOnClickListener(this);
    btnFin.setOnClickListener(this);
  }

  public void onClick(View src) {

    Intent intent = new Intent(this, MusicReceiver.class);
    switch (src.getId()) {
      case R.id.btnCancion:
        Toast.makeText(this, "Seleccionado Cancion", Toast.LENGTH_LONG).show();
        intent.putExtra("msg", "Inciar Cancion");
        //startService(intent);
        sendBroadcast(intent);
        break;
      case R.id.btnSonido:
        Toast.makeText(this, "Seleccionado Sonido", Toast.LENGTH_LONG).show();
        intent.putExtra("msg", "Iniciar Sonido");
        //startService(intent);
        sendBroadcast(intent);
        break;
      case R.id.btnFin:
        Toast.makeText(this, "Seleccionado Detener", Toast.LENGTH_LONG).show();
        intent.putExtra("msg", "Detener");
        //stopService(intent);
        sendBroadcast(intent);
        break;
      default:
        break;
    }
  }
}