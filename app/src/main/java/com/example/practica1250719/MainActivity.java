package com.example.practica1250719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText ncontrol,nombre,apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=(EditText)findViewById(R.id.et1);
        apellido=(EditText)findViewById(R.id.et2);
        ncontrol=(EditText)findViewById(R.id.et3);

    }
    public void registro(View view){
        basededatos admin= new basededatos(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();


        String nom= nombre.getText().toString();
        String ape= apellido.getText().toString();
        String mat= ncontrol.getText().toString();

        if (!nom.isEmpty()&&!ape.isEmpty()&&!mat.isEmpty()){

            ContentValues registro= new ContentValues();
            registro.put("control",mat);
            registro.put("nombre",nom);
            registro.put("apellido",ape);

            BaseDeDatos.insert("alumno",null,registro);
            BaseDeDatos.close();

            Toast.makeText(this,"Se registro con Exitoso",Toast.LENGTH_LONG).show();
            nombre.setText("");
            apellido.setText("");
            ncontrol.setText("");

        }else{
            if (nom.isEmpty())
                nombre.setError("Escribe el nombre");
            if (ape.isEmpty())
                apellido.setError("Escribe el apellido");
            if (mat.isEmpty())
                ncontrol.setError("Escribe tu numero de control");
//            Toast.makeText(this, "INGRESA EL VALOR RESTANTE", Toast.LENGTH_SHORT).show();
        }


    }
    public void buscar(View view){
        basededatos admin= new basededatos(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String mat = ncontrol.getText().toString();
        if(!mat.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery("select nombre,apellido from alumno where control=" + mat, null);
            if (fila.moveToFirst()) {
                nombre.setText(fila.getString(0));
                apellido.setText(fila.getString(1));
                BaseDeDatos.close();
            }
        }else{
                ncontrol.setError("NUMERO DE CONTROL NO REGISTRADO");
                ncontrol.setText("");
            }


        //else{
         //   ncontrol.setError("Ingrese numero de control ");
      // }

    }
    public void modificar(View view){
        basededatos admin= new basededatos(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String mat = ncontrol.getText().toString();
        String nom = nombre.getText().toString();
        String ape = apellido.getText().toString();



        if (!nom.isEmpty()&&!ape.isEmpty()&&!mat.isEmpty()){

            ContentValues modificar= new ContentValues();

            modificar.put("nombre",nom);
            modificar.put("apellido",ape);

            BaseDeDatos.update("alumno", modificar, "control="+mat, null);
            BaseDeDatos.close();
            Toast.makeText(this, "USUARIO MODIFICADO", Toast.LENGTH_LONG).show();
            nombre.setText("");
            apellido.setText("");
            ncontrol.setText("");
        }else{
            if (nom.isEmpty())
                nombre.setError("Escribe el nombre");
            if (ape.isEmpty())
                apellido.setError("Escribe el apellido");
            if (mat.isEmpty())
                ncontrol.setError("Escribe tu numero de control");
        }

    }

    public void eliminar(View view){
        basededatos admin= new basededatos(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String mat = ncontrol.getText().toString();
        if(!mat.isEmpty()){
            BaseDeDatos.delete("alumno","control="+mat,null);
            BaseDeDatos.close();
            Toast.makeText(this,"REGISTRO ELIMINADO",Toast.LENGTH_SHORT).show();
            nombre.setText("");
            apellido.setText("");
            ncontrol.setText("");


        }else{
            ncontrol.setError("NUMERO DE CONTROL INVALIDO ");
        }


    }

}


