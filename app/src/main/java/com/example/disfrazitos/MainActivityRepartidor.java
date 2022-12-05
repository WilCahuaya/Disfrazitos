package com.example.disfrazitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.disfrazitos.FragmentosRepartidor.EntregadosRepartidorFragment;
import com.example.disfrazitos.FragmentosRepartidor.InicioRepartidorFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivityRepartidor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    TextView txt_nombre_repartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_repartidor);
        txt_nombre_repartidor=findViewById(R.id.txt_nombre_repartidor);

        mAuth=FirebaseAuth.getInstance();

        Toolbar toolbar =findViewById(R.id.toolbar_repartidor);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout_repartidor);

        NavigationView navigationView=findViewById(R.id.nav_view_repartidor);
        navigationView.setNavigationItemSelectedListener(this);

        //color a los iconos
        navigationView.setItemIconTintList(null);
        txt_nombre_repartidor=navigationView.getHeaderView(0).findViewById(R.id.txt_nombre_repartidor);
        txt_nombre_repartidor.setText(Util.NOMBRE_CLIENTE);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_repartidor, new InicioRepartidorFragment()).commit();
            navigationView.setCheckedItem(R.id.inicio_repartidor);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.inicio_repartidor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_repartidor, new InicioRepartidorFragment()).commit();
                break;
            case R.id.elegir_rol_repartidor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_repartidor, new EntregadosRepartidorFragment()).commit();
                break;
            case R.id.cerrar_sesion_repartidor:
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainActivityRepartidor.this,MainActivityLogin.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}