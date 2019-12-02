package com.example.aadpractica3.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aadpractica3.model.data.Equipo;
import com.example.aadpractica3.model.data.Jugador;
import com.example.aadpractica3.model.rest.ApiClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private ApiClient apiClient;
    private String url = "54.157.35.41";

    public Repository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + url + "/web/Equipo/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClient = retrofit.create(ApiClient.class);
        fetchEquipoList();
        fetchJugadorList();
    }

    public void setUrl() {
        retrieveApiClient(url);
    }

    private void retrieveApiClient(String url) {
        this.url = url;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + url + "/web/Equipo/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClient = retrofit.create(ApiClient.class);

    }

    /* EQUIPO */
    private List<Equipo> equipoList = new ArrayList<>();
    private MutableLiveData<List<Equipo>> liveEquipoList = new MutableLiveData<>();

    public void fetchEquipoList(){
        Call<ArrayList<Equipo>> call = apiClient.getEquipos();
        call.enqueue(new Callback<ArrayList<Equipo>>() {
            @Override
            public void onResponse(Call<ArrayList<Equipo>> call, Response<ArrayList<Equipo>> response) {
                Log.v("xyz", "onResponse EQUIPO:" + response.body().toString());
                equipoList = response.body();
                Log.v("bat", "bin");
                liveEquipoList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Equipo>> call, Throwable t) {
                Log.v("xyz", "fetch EQUIPO: " + t.getLocalizedMessage());
                Log.v("bat", "man");
                equipoList = new ArrayList<>();
            }
        });
    }

    public List<Equipo> getEquipoList() { return equipoList; }

    public LiveData<List<Equipo>> getLiveEquipoList() { return liveEquipoList; }

    public void addEquipo(Equipo equipo) {
        Call<Long> call = apiClient.postEquipo(equipo);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Log.v("xyz", ""+response.body());

                long resultado = response.body();
                if (resultado > 0) {
                    fetchEquipoList();
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.v("xyz", t.getLocalizedMessage());
            }
        });
    }

    public void deleteEquipo(Equipo equipo){
        int idEquipo = equipo.getId();
        Call<Integer> call = apiClient.deleteEquipo(idEquipo);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("delete", "SUCCESS");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("delete", t.getLocalizedMessage());
            }
        });
    }

    public void borrarEquipo(Equipo equipo){ deleteEquipo(equipo);}

    public void editEquipo(Equipo equipo){
        int idEquipo = equipo.getId();
        Call<Integer> call = apiClient.putEquipo(idEquipo, equipo);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("edit", "SUCCESS");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("edit", t.getLocalizedMessage());
            }
        });
    }

    public void editarEquipo(Equipo equipo){ editEquipo(equipo); }

    /* JUGADOR */

    private List<Jugador> jugadorList = new ArrayList<>();
    private MutableLiveData<List<Jugador>> liveJugadorList = new MutableLiveData<>();

    public void fetchJugadorList(){
        Call<ArrayList<Jugador>> call = apiClient.getJugadores();
        call.enqueue(new Callback<ArrayList<Jugador>>() {
            @Override
            public void onResponse(Call<ArrayList<Jugador>> call, Response<ArrayList<Jugador>> response) {
                Log.v("xyz", "onResponse JUGADOR:" + response.body().toString());
                jugadorList = response.body();
                liveJugadorList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Jugador>> call, Throwable t) {
                Log.v("xyz", "fetch JUGADOR: " + t.getLocalizedMessage());
                jugadorList = new ArrayList<>();
            }
        });
    }

    public List<Jugador> getJugadorList() { return jugadorList; }

    public LiveData<List<Jugador>> getLiveJugadorList() { return liveJugadorList; }

    public void addJugador(Jugador jugador) {
        Call<Long> call = apiClient.postJugador(jugador);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Log.v("xyz", response.body().toString());

                long resultado = response.body();
                if (resultado > 0) {
                    fetchJugadorList();
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.v("xyz", t.getLocalizedMessage());
            }
        });
    }

    public void deleteJugador(Jugador jugador){
        int idJugador = jugador.getId();
        Call<Integer> call = apiClient.deleteJugador(idJugador);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("delete", "SUCCESS");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("delete", t.getLocalizedMessage());
            }
        });
    }

    public void borrarJugador(Jugador jugador){ deleteJugador(jugador);}

    public void editJugador(Jugador jugador){
        int idJugador = jugador.getId();
        Call<Integer> call = apiClient.putJugador(idJugador, jugador);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("edit", "SUCCESS");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("edit", t.getLocalizedMessage());
            }
        });
    }

    public void editarJugador(Jugador jugador){ editJugador(jugador); }

    //subir imagen
    public void upload(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part request = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Call<Integer> call = apiClient.fileUpload(request);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("xyz", ""+response.body());
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("xyz", t.getLocalizedMessage());
            }
        });
    }

}
