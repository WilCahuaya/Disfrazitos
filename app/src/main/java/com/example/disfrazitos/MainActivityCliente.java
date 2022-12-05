package com.example.disfrazitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.disfrazitos.FragmentosCliente.EditarPerfilClienteFragment;
import com.example.disfrazitos.FragmentosCliente.InicioClienteFragment;
import com.example.disfrazitos.FragmentosCliente.MisPedidosClienteFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivityCliente extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
TextView txt_nombre_cliente;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);


        this.setTitle(R.string.inicio_cliente);

        mAuth=FirebaseAuth.getInstance();

        Toolbar toolbar =findViewById(R.id.toolbar_cliente);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout_cliente);

        NavigationView navigationView=findViewById(R.id.nav_view_cliente);
        navigationView.setNavigationItemSelectedListener(this);


        //color a los iconos
        navigationView.setItemIconTintList(null);
        txt_nombre_cliente=navigationView.getHeaderView(0).findViewById(R.id.txt_nombre_cliente);
        txt_nombre_cliente.setText(Util.NOMBRE);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_cliente, new InicioClienteFragment()).commit();
            navigationView.setCheckedItem(R.id.inicio_cliente);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.inicio_cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_cliente, new InicioClienteFragment()).commit();
                break;
            case R.id.pedidos_cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_cliente, new MisPedidosClienteFragment()).commit();
                break;
            case R.id.editar_perfil_cliente:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_cliente, new EditarPerfilClienteFragment()).commit();
                break;
            case R.id.cerrar_sesion_cliente:
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainActivityCliente.this,MainActivityLogin.class));
                Toast.makeText(this, "Cerraste sesión", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}