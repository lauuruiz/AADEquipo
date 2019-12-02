package com.example.aadpractica3;

import android.content.Intent;
import android.os.Bundle;

import com.example.aadpractica3.model.data.Equipo;
import com.example.aadpractica3.view.EquipoAdapter;
import com.example.aadpractica3.view.EquipoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class EquipoActivity extends AppCompatActivity {

    public static EquipoViewModel viewModel;
    static RecyclerView rvEquipo;
    public static String rutaFoto = "http://54.157.35.41/web/Equipo/public/upload/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EquipoActivity.this, AnadirActivity.class);
                startActivity(i);
            }
        });

        viewModel = ViewModelProviders.of(this).get(EquipoViewModel.class);
        viewModel.setUrl();
        final EquipoAdapter adapter = new EquipoAdapter(this);
        viewModel.getLiveEquipoList().observe(this, new Observer<List<Equipo>>() {
            @Override
            public void onChanged(List<Equipo> equipos) {
                adapter.setEquipo(equipos);
            }
        });

        rvEquipo = findViewById(R.id.rvEquipos);
        rvEquipo.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EquipoActivity.this, PlantillaActivity.class);
                startActivity(i);
            }
        });

        adapter.notifyDataSetChanged();
        rvEquipo.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_equipo clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
