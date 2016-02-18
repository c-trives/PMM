package com.trives.donutsshop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context context = this;


        final SqLiteHelperCenter helper = new SqLiteHelperCenter(this,"DBDonuts2",null,1);

        Button registrarButton = (Button) findViewById(R.id.registrarButton);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        final EditText editTextPassword = (EditText) findViewById(R.id.userPassword);
        final EditText editTextUser = (EditText) findViewById(R.id.userLogin);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
             * Recuperamos de la base de datos de usuario una lista de usuarios.
             * Empleando el cursor, nos movemos al primer registro, rellenamos el pojo de usuario y lo añadimos
             * a la lista de usuarios
             */
                SQLiteDatabase db = helper.getReadableDatabase();
                String query = "select * from usuarios";
                Cursor c = db.rawQuery(query, null);

                List<Usuarios> usuarios = new ArrayList<Usuarios>();
                if (c.moveToFirst()) {

                    do {
                        Usuarios usuario = new Usuarios();
                        usuario.setId(c.getInt(0));
                        usuario.setNombreUsuario(c.getString(1));
                        usuario.setPassword(c.getString(2));
                        usuario.setEmail(c.getString(3));
                        usuarios.add(usuario);

                    } while (c.moveToNext());


                }
                /**
                 *
                 * Recorremos los usuarios, si el nombre del usuario coincide con el contenido
                 * que recuperamos del edittext y con la contraseña ocurre lo mismo, metemos en preferencias el id del usuario,
                 * ponemos el status de logeado en true y incluimos tambien el nombre de usuario en la clase preference data
                 * esto nos permitira posteriormente mediante el contexto recuperar esas variables
                 *
                 */
                for (Usuarios usuario : usuarios) {


                    if ((usuario.getNombreUsuario().equalsIgnoreCase(editTextUser.getText().toString()))
                            && (usuario.getPassword().equalsIgnoreCase(editTextPassword.getText().toString()))) {


                        PreferenceData.setLoggedInUserId(context, String.valueOf(usuario.getId()));
                        PreferenceData.setUserLoggedInStatus(context, true);   // For set user loggedin status
                        PreferenceData.setLoggedInUser(context, usuario.getNombreUsuario());
                        Intent intent = new Intent(Login.this,Menu.class);
                        startActivity(intent);
                    }
                }
            }
        });



        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,RegistroUsuario.class);
                startActivity(intent);

            }
        });
    }

}
