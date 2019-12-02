package com.example.aadpractica3.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aadpractica3.model.Repository;
import com.example.aadpractica3.model.data.Jugador;

import java.util.List;

public class PlantillaViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Jugador>> jugadores;

    public PlantillaViewModel(@NonNull Application application){
        super(application);
        repository = new Repository();
        jugadores = repository.getLiveJugadorList();
    }

    public List<Jugador> getJugadorList() {
        return repository.getJugadorList();
    }

    public LiveData<List<Jugador>> getLiveJugadorList() {
        return repository.getLiveJugadorList();
    }

    public void addJugador(Jugador jugador) {
        repository.addJugador(jugador);
    }

    public void deleteJugador(Jugador jugador){
        repository.borrarJugador(jugador);
    }

    public void editJugador(Jugador jugador){
        repository.editarJugador(jugador);

    }

    public void setUrl() {
        repository.setUrl();
    }
}
