package es.unizar.eina.M117_quads.ui.quads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.text.TextUtils;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;

import es.unizar.eina.M117_quads.R;

/** Pantalla utilizada para la creación o edición de una nota */
public class QuadsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quads);


    Button btnCrear = findViewById(R.id.btnCrearQuad);
    btnCrear.setOnClickListener(v -> {
    Intent i = new Intent(QuadsActivity.this, ModificarQuadsActivity.class);
    i.putExtra("modo", "crear");
    startActivity(i);
    });

    //carga dinámica de los quads??

    }
}
