package com.diegocampos.evaluacionmodulo3dc.View.actividades;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.diegocampos.evaluacionmodulo3dc.Interactor.manejoCamara.Permisos;
import com.diegocampos.evaluacionmodulo3dc.R;
import com.diegocampos.evaluacionmodulo3dc.View.Fragmentos.FragEliminarRegistroViewImpl;
import com.diegocampos.evaluacionmodulo3dc.View.Fragmentos.FragRegistroEquipo;
import com.diegocampos.evaluacionmodulo3dc.View.Fragmentos.FragVerEquipos;
import com.diegocampos.evaluacionmodulo3dc.View.Fragmentos.FragmentBienvenida;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OtraActividad extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int TAKE_PICTURE = 101; //detecta si se tomo la foto con la camara del celular
    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200; //detectar la respuesta del usuario si es ok

    DrawerLayout myDrawer;
    NavigationView myNav;
    Toolbar myToolbar;

    Bitmap bitmap;
    List<Bitmap> listaFotos = new ArrayList<>();
    ImageView imagenN;
    TextView cabecera;

    String nombre = "", cod = "", img1 = "", img2 = "";

    ActionBarDrawerToggle toogle; //para implementar el icono de hamburguesa

    FragRegistroEquipo fragRegistro = new FragRegistroEquipo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_actividad);

        myDrawer = findViewById(R.id.myDrawerLayout);
        myNav = findViewById(R.id.myNavigationView);
        myToolbar = findViewById(R.id.myToolbar);


        nombre = getIntent().getStringExtra("x");

        //mostrar actionbar
        setSupportActionBar(myToolbar);

        //eventos click en items de navigationDrawer
        myNav.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.myFrame, new FragmentBienvenida())
                .commit();
        setTitle("Registrar equipo");


        //para activar icono hamburguesa
        toogle = new ActionBarDrawerToggle(this, myDrawer, myToolbar, R.string.drawer_open, R.string.drawer_close);
        toogle = setDrawerToogle();
        myDrawer.addDrawerListener(toogle); //para oir al icono de hamburguesa


        View header = myNav.getHeaderView(0);
        cabecera = header.findViewById(R.id.txtNombreCabecera);
        cabecera.setText(nombre);

    }

    public void permisoCamara(ImageView ima) {
        this.imagenN = ima;
        permisoCamaraGeneral();
    }

    public void permisoCamaraGeneral() {
        if (new Permisos(this, this).permisosGeneral() == 1) tomarFoto();

    }

    public void tomarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, TAKE_PICTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                listaFotos.add((Bitmap) data.getExtras().get("data"));
                bitmap = (Bitmap) data.getExtras().get("data");
                //invocamos al fragmento para mostrar imagen en su respectivo ImageView
                FragRegistroEquipo.mostrarImagen(this.imagenN, bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void permisosAlmacenamiento(String codigo) {
        cod = codigo;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) { //Apis mas antiguas < 28
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    guardarFoto1();
                    guardarFoto2();
                }
                else {
                    //api > 28 (Q)
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION_WRITE_STORAGE
                    );
                }
            } else {
                guardarFoto1();
                guardarFoto2();
            }
        } else {
            guardarFoto1();
            guardarFoto2();
        }
    }

    public void guardarFoto1() { //Android Q y posteriores
        OutputStream outputStream = null;
        File file = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { //versiones recientes
            ContentResolver resolver = getContentResolver(); //para manejar los values
            ContentValues values = new ContentValues(); //metadatos de imagenes tipo, render, etc

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
            String tiempo = formatter.format(new Date());

            //agrego el dueño de la foto
            String filename = cod + "@foto1" + "@" + tiempo;

            values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");
            values.put(MediaStore.Images.Media.IS_PENDING, 1); //1 la imagen se esta procesando

            Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY); //construir una ruta en android
            Uri imageUri = resolver.insert(collection, values); //insertando en memoria la ruta anterior


            try {
                outputStream = resolver.openOutputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


                values.clear();
                values.put(MediaStore.Images.Media.IS_PENDING, 0);
                resolver.update(imageUri, values, null, null);

        }
        else { //Apis mas antiguas < 28
                String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
                String tiempo = formatter.format(new Date());

                //agrego el dueño de la foto
                String filename = cod + "@foto1" + "@" + tiempo + ".jpg"; //nombre del archivo

                file = new File(imageDir, filename);

                try {
                    outputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }

            boolean saved = listaFotos.get(0).compress(Bitmap.CompressFormat.JPEG, 100, outputStream); //para la calidad y compresión del archivo
            if (saved) {
                //Toast.makeText(this, "Registro realizado OK!", Toast.LENGTH_SHORT).show();
            }

            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (file != null) {
                MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, null);

            }
        }


        public void guardarFoto2 () { //Android Q y posteriores
            OutputStream outputStream = null;
            File file = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { //versiones recientes
                ContentResolver resolver = getContentResolver(); //para manejar los values
                ContentValues values = new ContentValues(); //metadatos de imagenes tipo, render, etc

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
                String tiempo = formatter.format(new Date());

                //agrego el dueño de la foto
                String filename = cod + "@foto2" + "@" + tiempo;

                values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");
                values.put(MediaStore.Images.Media.IS_PENDING, 1); //1 la imagen se esta procesando

                Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY); //construir una ruta en android
                Uri imageUri = resolver.insert(collection, values); //insertando en memoria la ruta anterior


                try {
                    outputStream = resolver.openOutputStream(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                values.clear();
                values.put(MediaStore.Images.Media.IS_PENDING, 0);
                resolver.update(imageUri, values, null, null);
            }
            else { //Apis mas antiguas < 28
                String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
                String tiempo = formatter.format(new Date());

                //agrego el dueño de la foto
                String filename = cod + "@foto2" + "@" + tiempo + ".jpg"; //nombre del archivo

                file = new File(imageDir, filename);

                try {
                    outputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            boolean saved = listaFotos.get(1).compress(Bitmap.CompressFormat.JPEG, 100, outputStream); //para la calidad y compresión del archivo
            if (saved) {
                //Toast.makeText(this, "Registro OK", Toast.LENGTH_SHORT).show();
            }

            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (file != null) {
                MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, null);
            }
            // archivos();
        }

        @Override
        public void onConfigurationChanged (@NonNull Configuration newConfig){
            super.onConfigurationChanged(newConfig);
            toogle.onConfigurationChanged(newConfig);
        }

        @Override
        protected void onPostCreate (@Nullable Bundle savedInstanceState){
            super.onPostCreate(savedInstanceState);
            toogle.syncState();
        }

        private ActionBarDrawerToggle setDrawerToogle () {
            return new ActionBarDrawerToggle(this, myDrawer, myToolbar, R.string.drawer_open, R.string.drawer_close);
        }

        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            if (toogle.onOptionsItemSelected(item)) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            //para mostrar los fragmentos
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.nav_registro:
                    ft.replace(R.id.myFrame, new FragRegistroEquipo()).commit();
                    break;
                case R.id.nav_ver:
                    ft.replace(R.id.myFrame, new FragVerEquipos()).commit();
                    break;
                case R.id.nav_eliminar:
                    ft.replace(R.id.myFrame, new FragEliminarRegistroViewImpl()).commit();
                    break;
            }
            setTitle(item.getTitle()); //para mostrar el título
            myDrawer.closeDrawers(); //para cerrar drawer
            return true;
        }

}