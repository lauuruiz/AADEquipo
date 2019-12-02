package com.example.aadpractica3.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aadpractica3.AnadirJugadorActivity;
import com.example.aadpractica3.EquipoActivity;
import com.example.aadpractica3.PlantillaActivity;
import com.example.aadpractica3.R;
import com.example.aadpractica3.model.data.Jugador;

import java.util.List;

public class PlantillaAdapter extends RecyclerView.Adapter<PlantillaAdapter.JugadorHolder> {

    private LayoutInflater inflater;
    private List<Jugador> jugadorList;
    private View.OnClickListener listener;
    private PlantillaViewModel viewModel;
    private Context context;

    public PlantillaAdapter(Context context){
        viewModel = PlantillaActivity.viewModel;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public JugadorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_plantilla, parent, false);

        return new JugadorHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final JugadorHolder holder, final int position) {
        if(jugadorList != null){
            final Jugador current = jugadorList.get(position);
            holder.tvNombreJ.setText(current.getNombre());
            holder.tvApellido.setText(current.getApellidos());
            Glide.with(context).load(EquipoActivity.rutaFoto+current.getFoto()).error(R.drawable.futbolista).into(holder.imJugador);

            holder.btBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //actualizar adapter al borrar
                    new AlertDialog.Builder(context).setTitle("Borrar")
                            .setMessage("¿Seguro que quiere borrar?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "borrado", Toast.LENGTH_SHORT).show();
                                    viewModel.deleteJugador(current);
                                    notifyItemRemoved(position);
                                    jugadorList.remove(position);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "nada", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            });
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public void onClick(View view){
        if(listener != null){
            listener.onClick(view);
        }
    }

    @Override
    public int getItemCount() {
        int njugadores = 0;
        if(jugadorList != null){
            njugadores = jugadorList.size();
        }
        return njugadores;
    }

    public void setJugador(List<Jugador> jugadorList){
        this.jugadorList = jugadorList;
        notifyDataSetChanged();
    }

    public class JugadorHolder extends RecyclerView.ViewHolder{
        private final TextView tvNombreJ, tvApellido;
        private final ImageView imJugador;
        private final Button btBorrar;

        public JugadorHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreJ = itemView.findViewById(R.id.tvNombreJ);
            tvApellido = itemView.findViewById(R.id.tvApellido);

            imJugador = itemView.findViewById(R.id.imJugador);

            btBorrar = itemView.findViewById(R.id.btBorrar);
        }
    }
}