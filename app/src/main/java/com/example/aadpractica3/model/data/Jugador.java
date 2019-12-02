package com.example.aadpractica3.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Jugador implements Parcelable {
    private int id,idequipo;
    private String nombre, apellidos, foto;

    public Jugador(){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.foto = foto;
        this.idequipo = idequipo;
    }

    public Jugador(int id, String nombre, String apellidos, String foto, int idequipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.foto = foto;
        this.idequipo = idequipo;
    }

    protected Jugador(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        apellidos = in.readString();
        foto = in.readString();
        idequipo = in.readInt();
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getidequipo() {
        return idequipo;
    }

    public void setidequipo(int idequipo) {
        this.idequipo = idequipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", foto='" + foto + '\'' +
                ", idequipo=" + idequipo +
                '}';
    }

    @Override
    public int describeContents(){ return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(foto);
        dest.writeInt(idequipo);
    }
}
