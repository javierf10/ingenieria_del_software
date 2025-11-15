package es.unizar.eina.M117_quads.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.ui.quads.QuadsActivity;

/** Pantalla principal de la aplicación Notepad */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Button btnQuads = findViewById(R.id.btnQuads);
    Button btnReservas = findViewById(R.id.btnReservas);


    btnQuads.setOnClickListener(v -> {
    Intent i = new Intent(MainActivity.this, QuadsActivity.class);
    startActivity(i);
    });


    btnReservas.setOnClickListener(v -> {
    // futura pantalla de reservas
    });
    }
}

