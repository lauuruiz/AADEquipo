package com.example.aadpractica3.model.rest;

import com.example.aadpractica3.model.data.Equipo;
import com.example.aadpractica3.model.data.Jugador;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiClient {
    // OPERACIONES DE EQUIPÒ

    @DELETE("equipo/{id}")
    Call<Integer> deleteEquipo(@Path("id") long id);

    @GET("equipo/{id}")
    Call<Equipo> getEquipo(@Path("id") long id);

    @GET("equipo")
    Call<ArrayList<Equipo>> getEquipos();

    @POST("equipo")
    Call<Long> postEquipo(@Body Equipo equipo);

    @PUT("equipo/{id}")
    Call<Integer> putEquipo(@Path("id") long id, @Body Equipo equipo);


    //OPERACIONES DE JUGADOR

    @DELETE("jugador/{id}")
    Call<Integer> deleteJugador(@Path("id") int id);

    @GET("jugador/{id}")
    Call<Jugador> getJugador(@Path("id") int id);

    @GET("jugador")
    Call<ArrayList<Jugador>> getJugadores();

    @POST("jugador")
    Call<Long> postJugador(@Body Jugador jugador);

    @PUT("jugador/{id}")
    Call<Integer> putJugador(@Path("id") int id, @Body Jugador jugador);

    //subir imagen
    @Multipart
    @POST("upload")
    Call<Integer> fileUpload(@Part MultipartBody.Part file);

}
