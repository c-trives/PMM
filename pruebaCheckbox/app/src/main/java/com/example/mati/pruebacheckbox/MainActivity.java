package com.example.mati.pruebacheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chkBoxCycling;
    CheckBox chkBoxTeaching;
    CheckBox chkBoxBlogging;
    RadioGroup radioGroup;
    Button btnHobby;
    TextView txtHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialUISetup();

    }

    public void initialUISetup()
    {
        chkBoxCycling = (CheckBox) findViewById(R.id.chkBoxCycling);
        chkBoxTeaching = (CheckBox) findViewById(R.id.chkBoxTeaching);
        chkBoxBlogging = (CheckBox) findViewById(R.id.chkBoxBlogging);

        final String[] strMessage = {"","",""};

        chkBoxBlogging.setOnCheckedChangeListener(new CheckedChange(strMessage));

        chkBoxTeaching.setOnCheckedChangeListener(new CheckedChange(strMessage));

        chkBoxCycling.setOnCheckedChangeListener(new CheckedChange(strMessage));

        txtHobby = (TextView) findViewById(R.id.txtHobby);

    }

    public void getHobbyClick(View v)
    {
        String strMessage = "";
        if(chkBoxCycling.isChecked())
        {
            strMessage+="Cycling ";
        }
        if(chkBoxTeaching.isChecked())
        {
            strMessage+="Teaching ";
        }
        if(chkBoxBlogging.isChecked())
        {
            strMessage+="Blogging ";
        }
        showTextNotification(strMessage);
    }
    public void showTextNotification(String msgToDisplay)
    {
        txtHobby.setText(msgToDisplay);
//Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
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

    class CheckedChange implements CompoundButton.OnCheckedChangeListener {

        String[] strMessage ;

       public CheckedChange(String[] strMessage){

            this.strMessage = strMessage;

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(buttonView == chkBoxCycling){

                    if(isChecked){
                        strMessage[0] = "Cycling";
                    }else{
                        strMessage[0] = "";
                    }
                }

                if(buttonView == chkBoxTeaching){
                    if(isChecked){
                        strMessage[1] = "Teaching";
                    }else{
                        strMessage[1] = "";
                    }
                }

                if(buttonView == chkBoxBlogging){
                    if(isChecked){
                      strMessage[2] = "Blogging";
                    }else{
                        strMessage[2] = "";
                    }
                }

            showTextNotification(strMessage[0] + strMessage[1] + strMessage[2]);

        }

/**
 * AL SER UN OBJETO LA VARIABLE NO ACUMULA
 */
     /**   @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            String strMessage = "";

            if(isChecked)
            {
                if(buttonView==chkBoxCycling)
                {
                    strMessage += " Cycling";
                }
                if(buttonView==chkBoxTeaching)
                {
                    strMessage += " Teaching";
                }
                if(buttonView==chkBoxBlogging)
                {
                    strMessage += " Blogging";
                }
            }

            showTextNotification(strMessage);

        }*/
    }

}

