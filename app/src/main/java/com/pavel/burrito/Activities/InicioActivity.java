package com.pavel.burrito.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.pavel.burrito.R;

public class InicioActivity extends AppCompatActivity {
    private ImageButton imgSendActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        imgSendActivity = findViewById(R.id.sendActivity);

        imgSendActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioActivity.this , NotaActivity.class);
                startActivity(i);
            }
        });
    }
}
