package com.android.mdw.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat;

import java.io.IOException;

public class Main extends Activity implements OnClickListener {

  EarBudsReceiver receiver;
  IntentFilter receiver_filter;
  private int PICK_SONG = 1;



  @Override public void onResume() {
    receiver_filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    registerReceiver(receiver, receiver_filter);
    super.onResume();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    receiver_filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    receiver = new EarBudsReceiver();
    registerReceiver(receiver, receiver_filter);


    Button btnCancion = (Button) findViewById(R.id.btnCancion);
    Button btnSonido = (Button) findViewById(R.id.btnSonido);
    Button btnFin = (Button) findViewById(R.id.btnFin);
    Button btnUri = (Button) findViewById(R.id.repUri);
    Button btnAud = (Button) findViewById(R.id.repAud);

    btnCancion.setOnClickListener(this);
    btnSonido.setOnClickListener(this);
    btnFin.setOnClickListener(this);
    btnAud.setOnClickListener(this);
    btnUri.setOnClickListener(this);
  }

  public void onClick(View src) {

    Intent intent = new Intent(this, MusicReceiver.class);
    Intent getIntent;
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
      case R.id.repAud:
        /*Toast.makeText(this, "Seleccionado RepCancion", Toast.LENGTH_LONG).show();
        intent.putExtra("msg", "Iniciar Cancion Seleccionada");
        //stopService(intent);
        sendBroadcast(intent);*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.MEDIA_CONTENT_CONTROL) != PackageManager.PERMISSION_GRANTED){
          ActivityCompat.requestPermissions(Main.this,
                  new String[]{Manifest.permission.MEDIA_CONTENT_CONTROL}, 1);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
          ActivityCompat.requestPermissions(Main.this,
                  new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
          ActivityCompat.requestPermissions(Main.this,
                  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        /*  Toast.makeText(this, "Seleccionar Audio", Toast.LENGTH_LONG).show();
          getIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
          startActivityForResult(getIntent, PICK_SONG);
        }*/


        Toast.makeText(this, "Seleccionar Audio", Toast.LENGTH_LONG).show();
        getIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(getIntent, PICK_SONG);


        break;
      case R.id.repUri:
        Toast.makeText(this, "Seleccionado Uri", Toast.LENGTH_LONG).show();
        getIntent = new Intent(this, ElServicio.class);
        intent.putExtra("msg", "uri");
        startService(getIntent);
        break;
      default:
        break;
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data){
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK){
      if(requestCode == PICK_SONG){
        Uri uri = data.getData();
        Intent intent = new Intent(this, ElServicio.class);
        //Bundle bundle = new Bundle();
        //bundle.putString("msg", "Seleccionar Audio");
        //bundle.putString("song", uri.toString());
        intent.putExtra("msg", uri.toString());
        startService(intent);
      }
    }

  }
}