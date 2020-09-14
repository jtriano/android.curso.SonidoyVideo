package com.example.sonido;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mpBebe;
    private MediaPlayer mpCoche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos nuestro video y la vista del mismo
        VideoView vista = findViewById(R.id.video);
        Uri ruta = Uri.parse("android.resource://com.example.sonido/" + R.raw.intromono);

        MediaController mc = new MediaController(this);
        // Establecemos las propiedades de la vista
        vista.setMediaController(mc);
        vista.setVideoURI(ruta);
        // Comenzamos el video
        vista.start();
    }

    public void pulsarBebe(View view) throws IOException {
        if ((mpBebe != null) && (!mpBebe.isPlaying())){
            mpBebe.prepare();  // Como se paró, es necesario preparar de nuevo nuestra instancia
            mpBebe.start();
            return;
        }
        if ((mpBebe != null) && (mpBebe.isPlaying())){ // Instancia creada y reproduciendose
            mpBebe.stop();                      // parar
            return;
        }
        if (mpBebe == null) {                 // Instancia no existe --> la creo y reproduzco
            mpBebe = MediaPlayer.create(this, R.raw.bebe);  // Crea la instancia y la prepara
            mpBebe.setLooping(true);
            mpBebe.start();
            return;
        }

    }

    public void pulsarCoche(View view) throws IOException {
        if (mpCoche == null){  // No hay una Instancia
            // Creo instancia, la preparo y la inicio
            mpCoche = MediaPlayer.create(this, R.raw.coche);
            mpCoche.start();
        } else {  // Hay una instancia
            if (mpCoche.isPlaying()) { // Si está reproduciendo
                // la detengo y la preparo para una nueva reprodución
                mpCoche.stop();
                mpCoche.prepare();
            } else {                    // si no está reproduciendo
                // la inicio
                mpCoche.start();
            }
        }
    }
}