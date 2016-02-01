package iesserpis.mati.cristian.profesorado;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Formulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        SQLiteHelperCenter helper = new SQLiteHelperCenter(this,"DBCentros2",null,1);
       final SQLiteDatabase db = helper.getWritableDatabase();


        final ProfesorDAO profesorDAO = new ProfesorDAO(this,db);
        Cursor c = db.rawQuery("SELECT * FROM centros", null);



        List<Centro> centros = new ArrayList<Centro>();
        if(c.moveToFirst()){

            do{
                Centro centro = new Centro();
                centro.setCod_centro(Integer.valueOf(c.getString(0)));
                centro.setNombre(c.getString(2));
                centro.setDireccion(c.getString(3));
                centro.setTelefono(c.getString(4));
                centros.add(centro);

            }while(c.moveToNext());


        }

        Centro[] centroArray = new Centro[centros.size()];

        for(int i=0;i<centroArray.length;i++){

            centroArray[i] = centros.get(i);

        }
        CentroAdapter adaptador =
                new CentroAdapter(this,centroArray);

        final Spinner spinnerCentros = (Spinner) findViewById(R.id.spinnerCentros);

        adaptador.setDropDownViewResource(R.layout.spinner_centro_formulario);

        spinnerCentros.setAdapter(adaptador);



       final EditText editTextDni = (EditText) findViewById(R.id.editTextDni);
        final  EditText editTextApellidos = (EditText) findViewById(R.id.editTextApellidos);
        final  EditText editTextEspecialidad = (EditText) findViewById(R.id.editTextEspecialidad);


        if(getIntent().getExtras() != null) {
            final Bundle bundle = getIntent().getExtras();
            Profesor profesor =(Profesor) bundle.getSerializable("profesor");



            for(int i =0;i<spinnerCentros.getAdapter().getCount();i++){

                Centro centro = (Centro) spinnerCentros.getItemAtPosition(i);
                if(centro.getCod_centro() == profesor.getCodigoCentro()){

                    spinnerCentros.setSelection(i);

                }

            }



            editTextApellidos.setText(profesor.getApellidos());
            editTextDni.setText(String.valueOf(profesor.getDni()));
            editTextEspecialidad.setText(profesor.getEspecialidad());

            editTextDni.setEnabled(false);

        }else{
            editTextDni.setEnabled(true);

        }

        findViewById(R.id.buttonFormAceptar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Centro centro = (Centro) spinnerCentros.getSelectedItem();

                Profesor profesor = new Profesor();
                Log.d("codCentro",String.valueOf(centro.getCod_centro()));
                profesor.setCodigoCentro(centro.getCod_centro());
                profesor.setDni(Integer.parseInt(editTextDni.getText().toString()));
                profesor.setApellidos(editTextApellidos.getText().toString());
                profesor.setEspecialidad(editTextEspecialidad.getText().toString());
                if(getIntent().getExtras() != null){
                    profesorDAO.editarProfesor(profesor);
                }else{
                    profesorDAO.insertarProfesor(profesor);
                }
                Intent intent = new Intent(Formulario.this, MainActivity.class);
                startActivity(intent);
                db.close();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
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
