package com.example.aadpractica3.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipo implements Parcelable {
    private int id, aforo;
    private String nombre, ciudad, estadio, escudo;

    public Equipo(){
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.aforo = aforo;
        this.escudo = escudo;
    }

    public Equipo(int id, String nombre, String ciudad, String estadio, int aforo, String escudo) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.aforo = aforo;
        this.escudo = escudo;
    }

    protected Equipo(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        ciudad = in.readString();
        estadio = in.readString();
        aforo = in.readInt();
        escudo = in.readString();
    }

    public static final Creator<Equipo> CREATOR = new Creator<Equipo>() {
        @Override
        public Equipo createFromParcel(Parcel in) {
            return new Equipo(in);
        }

        @Override
        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", aforo=" + aforo +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estadio='" + estadio + '\'' +
                ", escudo='" + escudo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(ciudad);
        dest.writeString(estadio);
        dest.writeInt(aforo);
        dest.writeString(escudo);
    }
}
