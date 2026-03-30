package com.myrsoft.dispositivosmovilestp2;

import androidx.core.util.Consumer;

/**
 * Clase generica para manejar eventos de una sola ejecucion en LiveData.
 */
public class Evento<T> {
    private final T contenido;
    private boolean haSidoManejado = false;

    public Evento(T contenido) {
        this.contenido = contenido;
    }

    /**
     * Ejecuta la accion solo si el evento no ha sido manejado aun.
     * Esto elimina la necesidad de usar "if" en la Activity.
     */
    public void ejecutarSiNoFueManejado(Consumer<T> accion) {
        if (!haSidoManejado) {
            haSidoManejado = true;
            accion.accept(contenido);
        }
    }

    public T leerContenido() {
        return contenido;
    }
}
