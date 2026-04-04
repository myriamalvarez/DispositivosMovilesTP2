package com.myrsoft.dispositivosmovilestp2;

import androidx.core.util.Consumer;

/**
 * Clase generica para manejar eventos de una sola ejecucion en LiveData.
 * 
 * Esta clase es un envoltorio (wrapper) fundamental.
 * Problema: Los LiveData comunes vuelven a emitir su ultimo valor si rotas el telefono 
 * (recreacion de la Activity), lo que haria que el Toast aparezca de nuevo por error.
 * 
 * Solucion: La clase Evento marca el contenido como "leido" una vez que se usa. 
 * Asi, el mensaje solo se muestra una unica vez, tal como pide la consigna.
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
     * T (Generics): Esto es lo mas importante. La T es un "comodin" para tipos de datos.
     * Si queremos mandar un mensaje, la T sera un String.
     * Si queremos mandar un numero, la T sera un Integer.
     * Esto permite que la clase Evento sirva para cualquier cosa sin tener que crear una
     * clase distinta para cada tipo de dato.
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
