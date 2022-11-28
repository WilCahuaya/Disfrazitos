package com.example.disfrazitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btn_cliente, btn_repartidor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cliente=findViewById(R.id.btn_cliente);
        btn_repartidor=findViewById(R.id.btn_repartidor);

        btn_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivityCliente.class);
                startActivity(intent);
            }
        });
        btn_repartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivityRepartidor.class);
                startActivity(intent);
            }
        });
    }
}