package com.example.mati.pruebaradiobox;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer miMusica= new MediaPlayer();

        final ArrayList<Integer> audioResourceId=new ArrayList<Integer>();
            Field[] fields=R.raw.class.getFields();
            for(int count=0; count < fields.length; count++){
                int resourceID= 0;//resource id
                try {
                    resourceID = fields[count].getInt(fields[count]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                audioResourceId.add(resourceID);
            }

        miMusica.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (index < audioResourceId.size())
                    index++;
                else
                    index=0;
                mp = MediaPlayer.create(this,audioResourceId(index));
                mp.start();
            }
        });



        final TextView lblMensaje = (TextView) findViewById(R.id.LblSeleccion);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.gruporb);
        rg.clearCheck();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String textoOpcion = "";
                if (group.getCheckedRadioButtonId() == R.id.radio1)
                    textoOpcion += "OPCION 1 con ID:" + checkedId;
                if (group.getCheckedRadioButtonId() == R.id.radio2)
                    textoOpcion += "OPCION 2 con ID:" + checkedId;
                lblMensaje.setText(textoOpcion);
            }
        });

        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rg.getCheckedRadioButtonId() == R.id.radio1){

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
