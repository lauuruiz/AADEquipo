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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aadpractica3.EditarActivity;
import com.example.aadpractica3.EquipoActivity;
import com.example.aadpractica3.PlantillaActivity;
import com.example.aadpractica3.R;
import com.example.aadpractica3.model.data.Equipo;

import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoHolder> {

    private LayoutInflater inflater;
    private List<Equipo> equipoList;
    private View.OnClickListener listener;
    private EquipoViewModel viewModel;
    private Context context;

    public EquipoAdapter(Context context){
        viewModel = EquipoActivity.viewModel;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public EquipoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_equipo, parent, false);

        return new EquipoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EquipoHolder holder, final int position) {
        if(equipoList != null){
            final Equipo current = equipoList.get(position);
            holder.tvNombre.setText(current.getNombre());
            holder.tvCiudad.setText(current.getCiudad());
            holder.tvEstadio.setText(current.getEstadio());
            holder.tvAforo.setText(Integer.toString(current.getAforo()));

            Glide.with(context).load(EquipoActivity.rutaFoto+current.getEscudo().toString()).error(R.drawable.futbol).into(holder.imEscudo);

            holder.imbtEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, EditarActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("equipo", current);
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });

            holder.imbtBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context).setTitle("Borrar")
                            .setMessage("¿Seguro que quiere borrar?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "borrado", Toast.LENGTH_SHORT).show();
                                    viewModel.deleteEquipo(current);
                                    notifyItemRemoved(position);
                                    equipoList.remove(position);
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


            final int id = current.getId();

            holder.cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "id "+id, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, PlantillaActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("equipo", current);
                    i.putExtra("id", id);
                    i.putExtras(bundle);
                    context.startActivity(i);
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
        int nequipos = 0;
        if(equipoList != null){
            nequipos = equipoList.size();
        }
        return nequipos;
    }

    public void setEquipo(List<Equipo> equipoList){
        this.equipoList = equipoList;
        notifyDataSetChanged();
    }

    public class EquipoHolder extends RecyclerView.ViewHolder{
        private final TextView tvNombre, tvCiudad, tvEstadio, tvAforo;
        private final ImageView imEscudo;
        private final ImageButton imbtEditar, imbtBorrar;
        ConstraintLayout cl;

        public EquipoHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCiudad = itemView.findViewById(R.id.tvCiudad);
            tvEstadio = itemView.findViewById(R.id.tvEstadio);
            tvAforo = itemView.findViewById(R.id.tvAforo);

            imEscudo = itemView.findViewById(R.id.imEscudo);

            imbtEditar = itemView.findViewById(R.id.imbtEditar);
            imbtBorrar = itemView.findViewById(R.id.imbtBorrar);

            cl = itemView.findViewById(R.id.cl);
        }
    }
}
