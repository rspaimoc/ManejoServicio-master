package com.android.mdw.demo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLOutput;

public class ElServicio extends Service {

	private MediaPlayer player;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show();
		player.stop();
		player.release();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startid) {

		AudioManager manager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		if(manager.isMusicActive()){
			player.stop();
			player.release();
		}

		String msg = intent.getStringExtra("msg");

		if (msg.equals("Iniciar Sonido")){
			Toast.makeText(this, "Servicio Sonido iniciado", Toast.LENGTH_LONG).show();
			player = MediaPlayer.create(this, R.raw.train);
		} else if (msg.equals("Inciar Cancion")) {

			Toast.makeText(this, "Servicio Cancion iniciado", Toast.LENGTH_LONG).show();
			player = MediaPlayer.create(this, R.raw.bob);

		} else {

			Toast.makeText(this, "Servicio Cancion Seleccionada iniciado", Toast.LENGTH_LONG).show();
			player = MediaPlayer.create(this, Uri.parse(msg));
		}
		player.setLooping(true);
		player.start();
		return startid;
	}	

}
