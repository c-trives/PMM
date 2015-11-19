package com.example.mati.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.media.*;

public class MainActivity extends AppCompatActivity {
    MediaPlayer miMusica = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miMusica=
                MediaPlayer.create(getApplicationContext(),R.raw
                        .dragonmiedo);

        miMusica.start();
        final TextView textView= (TextView) findViewById(R.id.miTxt);
        findViewById(R.id.miBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,Pantalla2.class);
                Bundle bundle = new Bundle();
                bundle.putString("Text", textView.getText().toString());
                intent.putExtras(bundle);
                bundle.putInt("posicionMusica", miMusica.getCurrentPosition());
                miMusica.pause();
                startActivityForResult(intent, 0);
                showToast("Entrando a Pantalla 2");
            }


        });

        findViewById(R.id.pararMusica).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                miMusica.stop();
            }
        });




    }

    @Override protected void onRestart(){
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();

    }
    @Override protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();


    }
    @Override protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    public void onActivityResult(int cod_resp, int cod_result,Intent intent){

        if(cod_result == RESULT_OK){

            Bundle bundleReturn = intent.getExtras();
           int posicionMusica= (int) bundleReturn.get("posicionMusica");
            miMusica.seekTo(posicionMusica);
            miMusica.start();
            TextView textViewReturn = (TextView) findViewById(R.id.returnText);
            textViewReturn.setText(bundleReturn.getString("return"));

        }
    }
        protected void showToast(CharSequence text){

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();

        }


    protected void showAlert(CharSequence text){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(text);
        alert.setPositiveButton(android.R.string.ok,null);
        alert.show();

    }








}
