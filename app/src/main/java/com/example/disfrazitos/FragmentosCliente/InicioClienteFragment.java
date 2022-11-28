package com.example.disfrazitos.FragmentosCliente;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.disfrazitos.R;

public class InicioClienteFragment extends Fragment {
    LinearLayout accessibility_action_clickable_span;
    DrawerLayout drawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_inicio_cliente, container, false);
        accessibility_action_clickable_span = vista.findViewById(R.id.accessibility_action_clickable_span);

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_cliente, new ShowDisfrazFragment()).commit();
        accessibility_action_clickable_span.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_cliente, new ShowDisfrazFragment());
                transaction.commit();
            }
        });
        return vista;
    }
}