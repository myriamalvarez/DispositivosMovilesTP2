package com.myrsoft.dispositivosmovilestp2;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.myrsoft.dispositivosmovilestp2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private DesbloqueoReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Usamos ViewBinding para acceder a los componentes del layout sin usar findViewById
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Instanciamos el ViewModel para manejar los datos y la logica
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        
        // Creamos el receiver pasandole el ViewModel para que se comuniquen
        receiver = new DesbloqueoReceiver(viewModel);

        configurarObservadores();
    }

    private void configurarObservadores() {
        //  Usamos observar para recibir eventos del ViewModel.
        // No usamos "if" aqui porque la clase Evento ya tiene la logica para ejecutarse una sola vez.
        
        viewModel.getEventoMostrarToast().observe(this, evento -> 
            evento.ejecutarSiNoFueManejado(mensaje -> 
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            )
        );
        //ejecutarSiNoFueManejado(...): Esta funcion es el "seguro".
        // Adentro tiene un if oculto que pregunta: "¿Ya mostre esto antes?".
        // Si la respuesta es NO, entonces ejecuta la funcion de llamada.
        // Si la respuesta es SI (porque rotaste la pantalla), ignora el pedido.
        viewModel.getEventoRealizarLlamada().observe(this, evento -> 
            evento.ejecutarSiNoFueManejado(this::ejecutarIntentLlamada)
        );
        // El simbolo :: es una forma abreviada de escribir una Lambda.
        // Esto nos permite que la pantalla se actualice sola sin tener que refrescarla manualmente.
        // Si necesitan cambiar algo en la pantalla segun el estado (ej. un loading), usen esto:
        viewModel.getEstadoUI().observe(this, this::renderizarEstado);
    }

    private void renderizarEstado(EstadoUI estado) {
        // Si agregan un ProgressBar u otros elementos, actualicen su visibilidad aqui.
        // El ViewModel les enviara el estado (CARGANDO, EXITO, ERROR).
    }

    private void ejecutarIntentLlamada(String numero) {
        // Intent implicito para abrir el marcador de llamadas
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + numero));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registramos el receiver por codigo porque ACTION_USER_PRESENT no permite registro en Manifest.
        // Usamos ContextCompat para que funcione en todas las versiones de Android sin usar "if".
        IntentFilter filtro = new IntentFilter(Intent.ACTION_USER_PRESENT);
        ContextCompat.registerReceiver(this, receiver, filtro, ContextCompat.RECEIVER_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Siempre desregistrar para evitar que la app consuma memoria de mas.
        unregisterReceiver(receiver);
    }
}
