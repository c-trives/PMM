package iesserpis.mati.cristian.pruebaexamen;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Zona[] datos = new Zona[]{new Zona("A",Double.parseDouble("30"),new String[]{"Asia","Oceania"},getDrawable(R.)),
            new Zona("B",Double.parseDouble("20"),new String[]{"America","Africa"}),
            new Zona("C",Double.parseDouble("10"),new String[]{"Europa"})
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinnerZonas = (Spinner)findViewById(R.id.spinnerZonas);

        final EditText editText = (EditText) findViewById(R.id.pesoPaquete);
        AdapterZona adapterZona = new AdapterZona(this);
        spinnerZonas.setAdapter(adapterZona);
        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.cajaRegalo);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.tarjetaRegalo);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        findViewById(R.id.buttonFin).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
              Zona zona = (Zona)  spinnerZonas.getSelectedItem();
                showToast(zona.getPrecio().toString());
                Double peso = Double.parseDouble(editText.getText().toString());
                String checkbox = checkBox1.getText().toString();
                String checkbox2 = checkBox2.getText().toString();
                RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                String tarifa = radioButton.getText().toString();

                Intent intent = new Intent(MainActivity.this,SegundaPantalla.class);
                Bundle bundle = new Bundle();

                bundle.putString("tarifa",tarifa);
                bundle.putDouble("peso",peso);

                if(!checkbox.isEmpty()){
                    bundle.putString("checkbox",checkbox);
                }
                if(!checkbox.isEmpty()){
                    bundle.putString("checkbox2",checkbox2);
                }
                intent.putExtras(bundle);
                startActivityForResult(intent,0);



            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
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

    class AdapterZona extends ArrayAdapter{

        Activity context;
        public AdapterZona(Activity context) {
            super(context, R.layout.spinnerlistzona, datos);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.spinnerlistzona, null);


            TextView lblZona = (TextView)item.findViewById(R.id.zona);
            lblZona.setText("Zona "+ datos[position].getZona());

            TextView lblNaciones= (TextView)item.findViewById(R.id.naciones);
            String nacionesString = "";
            String[] naciones = datos[position].getNaciones();
            for (int i=0;i<naciones.length;i++){
                nacionesString +=" " + naciones[i];
            }

            lblNaciones.setText(nacionesString);

            TextView lblPrecio = (TextView) item.findViewById(R.id.precio);
            lblPrecio.setText(datos[position].getPrecio().toString());

            return(item);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }
    }
}
