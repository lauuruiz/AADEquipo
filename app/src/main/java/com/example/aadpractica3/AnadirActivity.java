package com.example.aadpractica3;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aadpractica3.model.data.Equipo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

public class AnadirActivity extends AppCompatActivity {

    private String escudo = Integer.toString(R.drawable.futbol), nombre;
    private Uri uri;

    private EditText etNombre, etCiudad, etEstadio, etAforo, etEscudo;
    private ImageView imEquipo;
    private Button btImagen, btAnadir;

    public static final int PHOTO_SELECTED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        initComponents();
        initEvents();
    }

    private void initComponents(){
        etNombre = findViewById(R.id.etNombreA);
        etCiudad = findViewById(R.id.etCiudadA);
        etEstadio = findViewById(R.id.etEstadioA);
        etAforo = findViewById(R.id.etAforoA);

        imEquipo = findViewById(R.id.imEquipoA);

        btImagen = findViewById(R.id.btImagenA);
        btAnadir = findViewById(R.id.btAnadir);
    }

    private void initEvents(){
        btImagen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                seleccionarImagen();
                Glide.with(v.getContext())
                        .asBitmap()
                        .load(uri)
                        .error(R.drawable.futbolista)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imEquipo);
            }
        });

        btAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Equipo equipo = new Equipo();
                equipo.setNombre(etNombre.getText().toString());
                equipo.setCiudad(etCiudad.getText().toString());
                equipo.setEstadio(etEstadio.getText().toString());
                equipo.setAforo(Integer.parseInt(etAforo.getText().toString()));
                equipo.setEscudo(nombre);
                //finish();
                Intent i = new Intent(v.getContext(), EquipoActivity.class);
                startActivity(i);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
        intent.setType("*/*");
        String[] types = {"image/*"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, types);
        startActivityForResult(intent, PHOTO_SELECTED);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SELECTED && resultCode == Activity.RESULT_OK && null != data) {
            Uri uri = data.getData();
            this.uri = uri;
            saveSelectedImageInFile(uri);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveSelectedImageInFile(Uri uri) {
        Bitmap bitmap = null;
        if(Build.VERSION.SDK_INT < 28) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                bitmap = null;
            }
        } else {
            try {
                final InputStream in = this.getContentResolver().openInputStream(uri);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                //ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), uri);
                //bitmap = ImageDecoder.decodeBitmap(source);
            } catch (IOException e) {
                bitmap = null;
            }
        }
        if(bitmap != null) {
            File file = saveBitmapInFile(bitmap);
            if(file != null) {
                EquipoActivity.viewModel.upload(file);
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private File saveBitmapInFile(Bitmap bitmap) {
        nombre = "img"+ LocalDateTime.now()+"equipo.jpg";
        File file = new File(getFilesDir(), nombre);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            file = null;
        }
        return file;
    }
}

