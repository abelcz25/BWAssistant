package com.pavel.burrito.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pavel.burrito.R;

public class DataActivity extends AppCompatActivity {
    private TextView iNota , iTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        iNota = findViewById(R.id.nota);
        iTitulo =  findViewById(R.id.titulo);

        Intent intent = getIntent();
        String eeTitulo, eenota;
        eeTitulo = intent.getStringExtra("Title");
        eenota = intent.getStringExtra("Note");

        iTitulo.setText(eeTitulo);
        iNota.setText(eenota);

    }
}
