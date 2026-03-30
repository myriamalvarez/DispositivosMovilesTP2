package com.myrsoft.dispositivosmovilestp2;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Notas para el equipo:
 * 1. El ViewModel NO debe tener Context ni Toast.
 * 2. Solo emite estados y eventos que la Activity observa.
 */
public class MainViewModel extends ViewModel {

    // Estado de la interfaz (Cargando, Exito, Error)
    private final MutableLiveData<EstadoUI> estadoUI = new MutableLiveData<>(EstadoUI.EXITO);
    public LiveData<EstadoUI> getEstadoUI() {
        return estadoUI;
    }

    // Eventos de una sola ejecucion (se borran al ser leidos)
    private final MutableLiveData<Evento<String>> eventoMostrarToast = new MutableLiveData<>();
    public LiveData<Evento<String>> getEventoMostrarToast() {
        return eventoMostrarToast;
    }

    private final MutableLiveData<Evento<String>> eventoRealizarLlamada = new MutableLiveData<>();
    public LiveData<Evento<String>> getEventoRealizarLlamada() {
        return eventoRealizarLlamada;
    }

    /**
     * Se llama desde el Receiver cuando detecta el desbloqueo.
     */
    public void notificarDesbloqueo() {
        // Logcat segun consigna
        Log.d("MVVM_LOG", "Evento de desbloqueo procesado en el ViewModel");

        // Notificamos que queremos mostrar un Toast
        eventoMostrarToast.setValue(new Evento<>("Pantalla desbloqueada detectada"));

        // Notificamos que queremos abrir las llamadas con el numero 2664553747
        eventoRealizarLlamada.setValue(new Evento<>("2664553747"));
    }
}
