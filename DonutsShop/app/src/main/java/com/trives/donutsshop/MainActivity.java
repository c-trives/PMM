package com.trives.donutsshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GlaseadoDonut[] listaGlaseados = new GlaseadoDonut[]{
            new GlaseadoDonut("Chocolate",Double.parseDouble("1")),
            new GlaseadoDonut("Rosa",Double.parseDouble("1.20")),
            new GlaseadoDonut("Chocolate Blanco",Double.parseDouble("1.10"))
    };
    private RellenoDonut[] listaRellenos = new RellenoDonut[]{
            new RellenoDonut("Cabello de angel",Double.parseDouble("1.30")),
            new RellenoDonut("Chocolate",Double.parseDouble("1")),
            new RellenoDonut("Relleno de fresa",Double.parseDouble("1.50"))
    };


    CheckBox chkBoxOreo;
    CheckBox chkBoxSprinkles;
    double extras = 0;
    PrecioDonut precioDonut = null;
    GlaseadoDonut glaseadoDonut;
    RellenoDonut rellenoDonut;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Dunkin' Donuts");

        precioDonut = new PrecioDonut((TextView) findViewById(R.id.precioFinal));

        final Spinner spinnerRellenoDonuts = (Spinner) findViewById(R.id.spinnerRellenoDonuts);
        final Spinner spinnerGlaseadoDonuts = (Spinner) findViewById(R.id.spinnerGlaseadoDonuts);

        GlaseadoDonutAdapter glaseadoDonutAdapter = new GlaseadoDonutAdapter(this);
        RellenoDonutAdapter rellenoDonutAdapter = new RellenoDonutAdapter(this);

        rellenoDonutAdapter.setDropDownViewResource(R.layout.spinner_dropdownlist_relleno_donut);
        glaseadoDonutAdapter.setDropDownViewResource(R.layout.spinner_dropdownlist_glaseado_donut);

        spinnerRellenoDonuts.setAdapter(rellenoDonutAdapter);
        spinnerGlaseadoDonuts.setAdapter(glaseadoDonutAdapter);



        final SqLiteHelperCenter helper = new SqLiteHelperCenter(this,"DBDonuts2",null,1);

        spinnerRellenoDonuts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                rellenoDonut = (RellenoDonut) spinnerRellenoDonuts.getSelectedItem();
                precioDonut.setPrecioRellenoDonuts(rellenoDonut.getPrecio());
                precioDonut.calculaPrecioDonut();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerGlaseadoDonuts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                glaseadoDonut = (GlaseadoDonut) spinnerGlaseadoDonuts.getSelectedItem();
                precioDonut.setPrecioGlaseadoDonuts(glaseadoDonut.getPrecio());
                precioDonut.calculaPrecioDonut();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        chkBoxOreo = (CheckBox) findViewById(R.id.checkBoxOreo);
        chkBoxSprinkles = (CheckBox) findViewById(R.id.checkBoxSprinkles);



        chkBoxOreo.setOnCheckedChangeListener(new CheckedChange());

        chkBoxSprinkles.setOnCheckedChangeListener(new CheckedChange());


        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupPedido);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String textoOpcion = "";
                if (group.getCheckedRadioButtonId() == R.id.radioDomicilio) {
                    precioDonut.setDomicilio(1);
                    precioDonut.calculaPrecioDonut();
                }
                if (group.getCheckedRadioButtonId() == R.id.radioLocal) {

                    precioDonut.setDomicilio(0);
                    precioDonut.calculaPrecioDonut();
                }

            }
        });

        /**
         *
         * Cuando el usuario cliquea para enviar el pedido, se realiza la funcion de calcula precioDonut,
         * que actualiza el precio del textview, despues de esto, prepara el intent para pasar a la
         * pantalla de resultado. Despues de esto, hacemos una query para recuperar el ultimo id de la tabla
         * pedidos, sino existe ningun registro, le sumamos 1 a la id de pedido para evitar la introduccion de un
         * id 0. Por ultimo, inserta un pedido mediante la query de querypedido  recuperando de PreferenceData el id del usuario
         * , tras esto, inserta un donut empleando la variable previa de idPedido.
         *
         */

        findViewById(R.id.buttonPedidoDonuts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                precioDonut.calculaPrecioDonut();

                Intent intent = new Intent(MainActivity.this,PantallaResultado.class);

                SQLiteDatabase db = helper.getWritableDatabase();
                String queryIdUltimo = "SELECT id FROM pedidos ORDER BY id DESC LIMIT 1;";

                Cursor cursor = db.rawQuery(queryIdUltimo, null);

                cursor.moveToFirst();
                int idPedido = 0;
                if(cursor.getCount() != 0){
                    idPedido = cursor.getInt(0);

                }
                idPedido+=1;

                cursor.close();
                String queryPedido = "insert into pedidos values ('"+idPedido+"','"+ PreferenceData.getLoggedInUserId(context)+"')";
                String queryDonut = "insert into donuts ('pedidos_id','glaseado','relleno','precio') values ('"+ idPedido+"','"+ glaseadoDonut.getNombre()+"','"+ rellenoDonut.getNombre()+"','"+ precioDonut.getPrecioFinal()+"')";


                db.execSQL(queryPedido);
                db.execSQL(queryDonut);
                Bundle bundle = new Bundle();
                bundle.putSerializable("glaseado",glaseadoDonut);
                bundle.putSerializable("relleno",rellenoDonut);
                bundle.putSerializable("precio",precioDonut);


                intent.putExtras(bundle);

                startActivity(intent);




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

    class CheckedChange implements CompoundButton.OnCheckedChangeListener {




        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (buttonView == chkBoxOreo) {

                if (isChecked) {
                    extras += 1;
                } else {

                    extras -= 1;
                }
            }

            if (buttonView == chkBoxSprinkles) {
                if (isChecked) {
                    extras += 1;
                } else {

                    extras -= 1;
                }
            }



            precioDonut.setExtras(extras);
            precioDonut.calculaPrecioDonut();
        }

    }



    class GlaseadoDonutAdapter extends ArrayAdapter {
        Activity context;

        public GlaseadoDonutAdapter(Activity context) {
            super(context, R.layout.spinner_list_glaseado_donut, listaGlaseados);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            if(item == null)
            { LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.spinner_list_glaseado_donut, null);
            }



            String nombreGlaseado =  listaGlaseados[position].getNombre();
            TextView lblNombreGlaseado = (TextView)item.findViewById(R.id.nombreGlaseado);
            lblNombreGlaseado.setText(nombreGlaseado);


            String precioGlaseado = listaGlaseados[position].getPrecio().toString();
            TextView lblPrecio = (TextView) item.findViewById(R.id.precioGlaseado);
            lblPrecio.setText(precioGlaseado+"€");


            return(item);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position,convertView,parent);
        }
    }

    class RellenoDonutAdapter extends ArrayAdapter {
        Activity context;

        public RellenoDonutAdapter(Activity context) {
            super(context, R.layout.spinner_list_relleno_donut, listaRellenos);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            if(item == null)
            { LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.spinner_list_relleno_donut, null);
            }



            String nombreRelleno =  listaRellenos[position].getNombre();
            TextView lblNombreRelleno = (TextView)item.findViewById(R.id.nombreRelleno);
            lblNombreRelleno.setText(nombreRelleno);


            String precioRelleno = listaRellenos[position].getPrecio().toString();
            TextView lblPrecio = (TextView) item.findViewById(R.id.precioRelleno);
            lblPrecio.setText(precioRelleno+"€");


            return(item);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {

            return getView(position,convertView,parent);
        }
    }
}
