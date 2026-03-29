package com.myrsoft.dispositivosmovilestp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class DesbloqueoReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean pantallaDesbloqueada = intent.getAction().equals("android.action.USER_PRESENT");

        //Muestra un toast si la pantalla está desbloqueada
        Toast.makeText(context, "Pantalla desbloqueada detectada", Toast.LENGTH_SHORT).show();

        //Registra en el logcat el desbloqueo
        Log.d("mensaje", "Pantalla desbloqueada detectada");

        //Intent implicito para llamar al profe

        Intent llamarAlProfe = new Intent(Intent.ACTION_CALL);
        llamarAlProfe.setData(Uri.parse("tel:2664553747"));
        llamarAlProfe.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(llamarAlProfe);
    }
}