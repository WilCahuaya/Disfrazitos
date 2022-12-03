package com.example.disfrazitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class MainActivityLogin extends AppCompatActivity {
    EditText edt_correo_login, edt_contrasena_login;
    Button btn_iniciar_sesion;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        mAuth=FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        edt_correo_login=findViewById(R.id.edt_correo_login);
        edt_contrasena_login=findViewById(R.id.edt_contrasena_login);
        btn_iniciar_sesion=findViewById(R.id.btn_iniciar_sesion);

        btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=edt_correo_login.getText().toString();
                String passw=edt_contrasena_login.getText().toString();

                if (email.isEmpty() || passw.isEmpty()){
                    Toast.makeText(MainActivityLogin.this, "Ingresar los datos completo", Toast.LENGTH_SHORT).show();
                }else{
                    iniciarSesion(email,passw);
                }
            }
        });

    }

    private void iniciarSesion(String email, String passw) {

        mAuth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    if(email.equals("cliente@gmail.com")){
                        startActivity(new Intent(MainActivityLogin.this,MainActivityCliente.class));
                    }else if (email.equals("repartidor@gmail.com")){
                        startActivity(new Intent(MainActivityLogin.this,MainActivityRepartidor.class));
                    }
                    Toast.makeText(MainActivityLogin.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivityLogin.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(MainActivityLogin.this, "Error al inisiar sesión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null){
            startActivity(new Intent(MainActivityLogin.this,MainActivityCliente.class));
            finish();
        }
    }
}