package com.example.aadpractica3.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aadpractica3.model.Repository;
import com.example.aadpractica3.model.data.Equipo;

import java.io.File;
import java.util.List;

public class EquipoViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Equipo>> equipos;

    public EquipoViewModel(@NonNull Application application){
        super(application);
        repository = new Repository();
        equipos = repository.getLiveEquipoList();
    }

    public List<Equipo> getEquipoList() {
        return repository.getEquipoList();
    }

    public LiveData<List<Equipo>> getLiveEquipoList() {
        return repository.getLiveEquipoList();
    }

    public void addEquipo(Equipo equipo) {
        repository.addEquipo(equipo);
    }

    public void deleteEquipo(Equipo equipo){
        repository.borrarEquipo(equipo);
    }

    public void editEquipo(Equipo equipo){
        repository.editarEquipo(equipo);

    }

    public void setUrl() {
        repository.setUrl();
    }

    public void upload(File file){repository.upload( file);}
}
