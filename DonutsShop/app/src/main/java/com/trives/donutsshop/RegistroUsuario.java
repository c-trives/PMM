package com.trives.donutsshop;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final SqLiteHelperCenter helper = new SqLiteHelperCenter(this,"DBDonuts2",null,1);

        Button userRegisterButton = (Button) findViewById(R.id.userRegisterButton);
        final EditText userRegisterUsuarioEdit =(EditText) findViewById(R.id.userRegisterUsuario);
        final EditText userRegisterEmailEdit = (EditText) findViewById(R.id.userRegisterEmail);
        final EditText userRegisterPasswordEdit = (EditText) findViewById(R.id.userRegisterPassword);

        userRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
                String query = "insert into usuarios('NOMBRE_USUARIO','PASSWORD','EMAIL') values ('"+userRegisterUsuarioEdit.getText().toString()+"','"+ userRegisterPasswordEdit.getText().toString()  +"','"+ userRegisterEmailEdit.getText().toString()+"')";
                sqLiteDatabase.execSQL(query);
                Intent intent = new Intent(RegistroUsuario.this,Login.class);
                startActivity(intent);
            }
        });

    }

}
