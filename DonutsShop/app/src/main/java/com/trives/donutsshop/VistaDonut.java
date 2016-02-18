package com.trives.donutsshop;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VistaDonut extends AppCompatActivity {
Donut[] donutsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /**
         *
         * Hacemos una query recuperando los donuts que ha pedido el usuario a lo largo del tiempo
         * mediante la segunda consulta que recupera los ids de los pedidos de ese usuario
         *
         */

        final SqLiteHelperCenter helper = new SqLiteHelperCenter(this,"DBDonuts2",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM donuts d where d.pedidos_id in (SELECT id from pedidos where user_id = "+PreferenceData.getLoggedInUserId(this)+")";

        Cursor c = db.rawQuery(query, null);
        /**
         *
         * Mueve el cursor al primer registro y hace un bucle rellenando pojos de donut
         * y añadiendolos a la lista de donuts
         *
         */
        List<Donut> donuts = new ArrayList<Donut>();

        if (c.moveToFirst()) {

            do {
                Donut donut = new Donut();
                donut.setId(c.getInt(0));
                donut.setGlaseado(c.getString(2));
                donut.setRelleno(c.getString(3));
                donut.setPrecio(Double.parseDouble(String.valueOf(c.getFloat(4))));
                donuts.add(donut);

            } while (c.moveToNext());


        }
        donutsArray =  new Donut[donuts.size()];
        donuts.toArray(donutsArray);
        /**
         *
         *Convierte el arraylist en un array y lo pasa al listview
         * para que lo pueda emplear el adapter
         *
         */
        ListView listViewDonut = (ListView) findViewById(R.id.listDonuts);

        RellenoDonutAdapter rellenoDonutAdapter = new RellenoDonutAdapter(this);
        listViewDonut.setAdapter(rellenoDonutAdapter);
    }

    class RellenoDonutAdapter extends ArrayAdapter {
        Activity context;

        public RellenoDonutAdapter(Activity context) {
            super(context, R.layout.layout_list_donuts, donutsArray);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            if(item == null)
            { LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.layout_list_donuts, null);
            }

          //  LayoutInflater inflater = context.getLayoutInflater();
          //  View item = inflater.inflate(R.layout.layout_list_donuts, null);

            String nombreGlaseado =  donutsArray[position].getGlaseado();
            TextView lblNombreGlaseado = (TextView)item.findViewById(R.id.nombreGlaseadoList);
            lblNombreGlaseado.setText(nombreGlaseado);

            String nombreRelleno =  donutsArray[position].getRelleno();
            TextView lblNombreRelleno = (TextView)item.findViewById(R.id.nombreRellenoList);
            lblNombreRelleno.setText(nombreRelleno);


            String precioFinalDonut = donutsArray[position].getPrecio().toString();
            TextView lblPrecioFinalDonut = (TextView) item.findViewById(R.id.precioFinalDonutList);
            lblPrecioFinalDonut.setText(precioFinalDonut+"€");


            return(item);

        }
    }

}
