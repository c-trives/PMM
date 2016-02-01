package iesserpis.mati.cristian.profesorado;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SQLiteHelperCenter helper = new SQLiteHelperCenter(this,"DBCentros2",null,1);

        SQLiteDatabase db = helper.getWritableDatabase();

        final ProfesorDAO profesorDAO = new ProfesorDAO(this,db);

        //String insert = "INSERT INTO profesores VALUES (10,4123005,'Bueno Zarco, Elisa', 'MATEMÁTICAS');";
        //db.execSQL(insert);
        //String insert = "INSERT INTO centros VALUES (15,'P','CP Los Danzantes', 'C/Las Musas s/n','985-112322',250)";
       // db.execSQL(insert);
        Cursor c = db.rawQuery("SELECT * FROM profesores", null);


        List<Profesor> profesores = new ArrayList<Profesor>();
        if(c.moveToFirst()){
            do{

                Profesor profesor = new Profesor(c.getInt(0),c.getInt(1),c.getString(2),c.getString(3));
                profesores.add(profesor);

                Toast.makeText(this, "Profesor Apellidos: " + profesor.getApellidos() + " DNI: " + profesor.getDni(), Toast.LENGTH_SHORT).show();
            }while(c.moveToNext());


        }



        final TableLayout tableLayout = (TableLayout) findViewById(R.id.dataTable);

        TableRow tableRow = null;
        for(Profesor profesor: profesores){
            tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            TextView textView = new TextView(this);
            textView.setText(String.valueOf(profesor.getCodigoCentro()));
            textView.setBackgroundColor(Color.WHITE);
            textView.setTextSize(10);
            TextView textView2 = new TextView(this);
            textView2.setText(String.valueOf(profesor.getDni()));
            textView2.setBackgroundColor(Color.WHITE);
            textView2.setTextSize(10);

            TextView textView3 = new TextView(this);
            textView3.setText(profesor.getApellidos());
            textView3.setBackgroundColor(Color.WHITE);
            textView3.setTextSize(10);

            TextView textView4 = new TextView(this);
            textView4.setText(profesor.getEspecialidad());
            textView4.setBackgroundColor(Color.WHITE);
            textView4.setTextSize(10);

            tableRow.addView(textView);

            tableRow.addView(textView2);

            tableRow.addView(textView3);

            tableRow.addView(textView4);

            if(tableRow != null){

                tableLayout.addView(tableRow);
            }
        }


        int countChild = tableLayout.getChildCount();
        final TableRow[] tableRowSeleccionada = {null};

        for(int i=1;i<countChild;i++){

            tableLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(tableRowSeleccionada[0] != null){
                        cambiarColorCelda(Color.WHITE, tableRowSeleccionada[0]);
                    }

                    TableRow tableRow = (TableRow) v;

                    cambiarColorCelda(Color.GRAY,tableRow);

                    tableRowSeleccionada[0] = tableRow;
                }
            });
        }


        findViewById(R.id.addButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Formulario.class);
                        startActivity(intent);

                    }
                }
        );
        findViewById(R.id.editButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        TextView textViewCodCentro = (TextView)tableRowSeleccionada[0].getChildAt(0);
                        TextView textViewDni = (TextView)tableRowSeleccionada[0].getChildAt(1);
                        TextView textViewApellidos = (TextView)tableRowSeleccionada[0].getChildAt(2);
                        TextView textViewEspecialidad = (TextView)tableRowSeleccionada[0].getChildAt(3);

                        Profesor profesor = new Profesor(Integer.valueOf(textViewCodCentro.getText().toString()),Integer.valueOf(textViewDni.getText().toString()),textViewApellidos.getText().toString(),textViewEspecialidad.getText().toString());
                        Bundle bundle = new Bundle();
                         bundle.putSerializable("profesor",profesor);




                         Intent intent = new Intent(MainActivity.this,Formulario.class);
                        intent.putExtras(bundle);
                          startActivity(intent);

                    }
                }
        );

        findViewById(R.id.deleteButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        TextView textViewCodCentro = (TextView)tableRowSeleccionada[0].getChildAt(0);
                        TextView textViewDni = (TextView)tableRowSeleccionada[0].getChildAt(1);
                        TextView textViewApellidos = (TextView)tableRowSeleccionada[0].getChildAt(2);
                        TextView textViewEspecialidad = (TextView)tableRowSeleccionada[0].getChildAt(3);

                        Profesor profesor = new Profesor(Integer.valueOf(textViewCodCentro.getText().toString()),Integer.valueOf(textViewDni.getText().toString()),textViewApellidos.getText().toString(),textViewEspecialidad.getText().toString());

                        profesorDAO.deleteProfesor(profesor);

                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);

                    }
                }
        );
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

    public Profesor cambiarColorCelda(int color,TableRow tableRow){

        Profesor profesor = new Profesor();
        String[] datos = new String[tableRow.getChildCount()];

        for(int i=0;i<tableRow.getChildCount();i++){
            TextView textView = (TextView) tableRow.getChildAt(i);
            textView.setBackgroundColor(color);
            datos[i] = textView.getText().toString();
        }

        profesor.setCodigoCentro(Integer.parseInt(datos[0]));
        profesor.setDni(Integer.parseInt(datos[1]));
        profesor.setApellidos(datos[2]);
        profesor.setApellidos(datos[3]);

        return profesor;

    }
}
