package iesserpis.mati.cristian.menuprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView lblMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblMensaje = (TextView)findViewById(R.id.lblMensaje);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //Alternativa 1
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                lblMensaje.setText("Opcion 1 pulsada!");
                return true;
            case R.id.MnuOpc2:
                lblMensaje.setText("Opcion 2 pulsada!");
                return true;
            case R.id.MnuOpc3:
                lblMensaje.setText("Opcion 3 pulsada!");
                switch (item.getItemId()){
                    case R.id.submenu1:
                        lblMensaje.setText("Submenu 1 pulsado");
                        return true;
                    default:
                        return super.onOptionsItemSelected(item);
                }
            default:
                return super.onOptionsItemSelected(item);
        }}
    }
