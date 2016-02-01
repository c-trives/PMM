package iesserpis.mati.cristian.databasetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ClientesSqLiteHelper helper = new ClientesSqLiteHelper(this,"DBClientes",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

      /**  if(db != null){
            for(int i=0;i<3;i++){

                int codigo = i;
                String nombreCliente = "Cliente0"+i;
                String telefono = i+"XXXXXXXX";

                db.execSQL("INSERT INTO clientes ('codigo','nombre','telefono') VALUES ('" + codigo + "','" + nombreCliente + "','" + telefono + "')");


            }
        }*/

        Cursor c = db.rawQuery("SELECT * FROM clientes",null);



        List<Cliente> clientes = new ArrayList<Cliente>();
        if(c.moveToFirst()){

            do{
                Cliente cliente = new Cliente(c.getInt(0),c.getString(1),c.getString(2));
                clientes.add(cliente);

                Toast.makeText(this,"Cliente: "+ cliente.getNombre(),Toast.LENGTH_SHORT).show();
            }while(c.moveToNext());


        }


        Cliente[] clienteArray = new Cliente[clientes.size()];

        for(int i=0;i<clienteArray.length;i++){

            clienteArray[i] = clientes.get(i);

        }
        ClienteAdapter adaptador =
                new ClienteAdapter(this,clienteArray);

        Spinner spinnerCliente = (Spinner)findViewById(R.id.SpinnerCliente);

        adaptador.setDropDownViewResource(R.layout.listitem_titular);

        spinnerCliente.setAdapter(adaptador);

        db.close();
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
