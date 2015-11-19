package com.example.mati.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Console;

import java.io.Console;

/**
 * Created by mati on 5/10/15.
 */
public class Pantalla2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);

        final TextView textLabelView = (TextView)findViewById(R.id.miLbl);

        final Bundle bundle = getIntent().getExtras();
        textLabelView.setText(bundle.getString("Text"));
        showToast(String.valueOf(bundle.getInt("posicionMusica")));


        findViewById(R.id.btnVolver).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent returnIntent = new Intent();
                bundle.putString("return", "El valor devuelto es " + textLabelView.getText());
                returnIntent.putExtras(bundle);
                setResult(RESULT_OK, returnIntent);
                showToast("Volviendo a pantalla 1");
                finish();

            }
        });

        findViewById(R.id.btnVolverImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                bundle.putString("return", "El valor devuelto es " + textLabelView.getText());
                returnIntent.putExtras(bundle);
                setResult(RESULT_OK, returnIntent);
                showToast("Volviendo a pantalla 1");
                finish();
            }


        });


    }
    @Override protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    protected void showToast(CharSequence text){

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}
