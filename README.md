# Proyecto Dispositivos Moviles - TP2

## Descripcion de la App
Esta aplicacion Android implementa un BroadcastReceiver para capturar el evento del sistema correspondiente al desbloqueo de la pantalla (Intent.ACTION_USER_PRESENT). 

Al detectar el desbloqueo, la aplicacion realiza las siguientes acciones:
1. Muestra un Toast con el mensaje: "Pantalla desbloqueada detectada".
2. Registra el evento en Logcat para seguimiento tecnico.
3. Genera un Intent implicito que abre el marcador de llamadas con el numero: 2664553747.

## Arquitectura y Buenas Practicas
El proyecto fue desarrollado bajo una arquitectura MVVM (Modelo-Vista-ViewModel) estricta:

- **MainActivity (Vista)**: Clase "tonta" que solo observa LiveData y renderiza la UI. 
No toma decisiones de negocio.
- **MainViewModel (Logica)**: Centraliza toda la logica. 
No utiliza Context ni accede a APIs de Android directamente, comunicandose 
con la vista mediante estados y eventos.
- **DesbloqueoReceiver**: Captura el evento del sistema y lo delega inmediatamente 
al ViewModel.
- **Evento (Event Wrapper)**: Soluciona el problema de los LiveData que repiten 
informacion al rotar el celular, asegurando que el mensaje de desbloqueo se 
dispare una sola vez.
- **EstadoUI (Enum)**: Gestiona los estados de la aplicacion de forma escalable sin 
usar variables booleanas.
- **ViewBinding**: Implementado para una referencia segura y eficiente a los 
elementos del layout.

## Requisitos de Compilacion
- Compile SDK: 34
- Java: 17 (JavaVersion.VERSION_17)
- Android Gradle Plugin: 8.4.2 o superior

## Integrantes del Grupo
- Alvarez, Myriam - 22717003
- Ricchiardi, Maria Romanela - 35842052
- Constante, Hernan - 41137674
- Paredes, Kevin - 39137254
