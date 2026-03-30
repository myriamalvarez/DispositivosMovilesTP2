package com.myrsoft.dispositivosmovilestp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Este receiver solo detecta el evento y avisa al ViewModel.
 * No debe mostrar mensajes ni hacer llamadas directamente.
 */
public class DesbloqueoReceiver extends BroadcastReceiver {

    private final MainViewModel viewModel;

    public DesbloqueoReceiver(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Verificamos que la accion sea la correcta
        if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            Log.d("APP_LOG", "Broadcast detectado: Pantalla desbloqueada");
            
            // Notificamos al ViewModel para que procese la logica
            if (viewModel != null) {
                viewModel.notificarDesbloqueo();
            }
        }
    }
}
