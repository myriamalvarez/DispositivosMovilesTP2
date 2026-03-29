package com.myrsoft.dispositivosmovilestp2;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private DesbloqueoReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new DesbloqueoReceiver();

        //Solicito permiso para llamadas
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Registramos el receiver
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_USER_PRESENT));
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Desregistramos el receiver
        unregisterReceiver(receiver);
    }

}