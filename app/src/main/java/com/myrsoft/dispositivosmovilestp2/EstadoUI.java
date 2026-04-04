package com.myrsoft.dispositivosmovilestp2;

/**
 * Representa los estados posibles de la interfaz de usuario.
 * 
 * En lugar de usar variables booleanas (como estaCargando = true),
 * usamos un Enum. Esto hace que el codigo sea mucho mas escalable y facil de 
 * leer para el equipo, ya que la UI siempre sabe en que estado exacto se 
 * encuentra la aplicacion.
 * 
 * En una app real se usaria CARGANDO para progreso y ERROR si algo falla.
 * Permite que la Activity sea "tonta" y solo reciba un estado sin evaluar 
 * variables sueltas.
 */
public enum EstadoUI {
    CARGANDO,
    EXITO,
    ERROR
}
