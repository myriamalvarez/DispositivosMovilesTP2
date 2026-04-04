package com.myrsoft.dispositivosmovilestp2;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel que gestiona la logica de la aplicacion.
 * Sigue las reglas de no usar Context ni clases de Android directas (UI).
 * Aqui decidimos que hacer cuando ocurre el evento.
 */
public class MainViewModel extends ViewModel {

    // LiveData privado para el estado de la UI y publico para la observacion
    private final MutableLiveData<EstadoUI> estadoUI = new MutableLiveData<>(EstadoUI.EXITO);
    
    // COMPANEROS: Exponemos el LiveData como publico para que la Activity lo observe,
    // pero mantenemos el MutableLiveData privado para que la Activity no pueda modificarlo.
    public LiveData<EstadoUI> getEstadoUI() {
        return estadoUI;
    }

    // LiveData para eventos de una sola ejecucion (Toast)
    private final MutableLiveData<Evento<String>> eventoMostrarToast = new MutableLiveData<>();
    public LiveData<Evento<String>> getEventoMostrarToast() {
        return eventoMostrarToast;
    }

    // LiveData para eventos de una sola ejecucion (Llamada)
    private final MutableLiveData<Evento<String>> eventoRealizarLlamada = new MutableLiveData<>();
    public LiveData<Evento<String>> getEventoRealizarLlamada() {
        return eventoRealizarLlamada;
    }

    /**
     * Metodo llamado cuando se detecta el desbloqueo de pantalla.
     * Centraliza la logica de respuesta al evento.
     */
    public void notificarDesbloqueo() {
        // Logcat segun requerimiento
        Log.d("MVVM_LOG", "Evento de desbloqueo procesado en el ViewModel");

        // Cambiamos el estado a EXITO para indicar que se proceso correctamente
        estadoUI.setValue(EstadoUI.EXITO);

        // Disparamos el evento para mostrar el mensaje solicitado
        // Usamos Evento para asegurar que solo se muestre una vez
        eventoMostrarToast.setValue(new Evento<>("Pantalla desbloqueada detectada"));

        // Disparamos el evento para abrir la aplicacion de llamadas con el numero solicitado
        eventoRealizarLlamada.setValue(new Evento<>("2664553747"));
    }
}
