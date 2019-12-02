package com.example.aadpractica3;

import android.content.Intent;
import android.os.Bundle;

import com.example.aadpractica3.model.data.Jugador;
import com.example.aadpractica3.view.PlantillaAdapter;
import com.example.aadpractica3.view.PlantillaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

public class PlantillaActivity extends AppCompatActivity {

    public static PlantillaViewModel viewModel;
    static RecyclerView rvJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla);

        final int idEquipo = getIntent().getIntExtra("id", 0);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlantillaActivity.this, AnadirJugadorActivity.class);
                i.putExtra("id", idEquipo);
                startActivity(i);
            }
        });

        viewModel = ViewModelProviders.of(this).get(PlantillaViewModel.class);
        viewModel.setUrl();
        final PlantillaAdapter adapter = new PlantillaAdapter(this);
        final List<Jugador> jugadores = new ArrayList<>();
        viewModel.getLiveJugadorList().observe(this, new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> jugadors) {
                for (int i = 0; i < jugadors.size(); i++){
                    if(jugadors.get(i).getidequipo() == idEquipo){
                        jugadores.add(jugadors.get(i));
                        adapter.setJugador(jugadores);
                    }
                }
            }
        });

        rvJugador = findViewById(R.id.rvJugador);
        rvJugador.setLayoutManager(new LinearLayoutManager(this));

        adapter.notifyDataSetChanged();
        rvJugador.setAdapter(adapter);
    }
}
