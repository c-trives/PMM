package iesserpis.mati.cristian.ctrivesexamen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    private Pizza[] listaPizzas = new Pizza[]{
            new Pizza("Margarita","jamon/tomate",Double.parseDouble("12")),
            new Pizza("Barbacoa","carne/tomate",Double.parseDouble("18")),
            new Pizza("Tres Quesos","Queso1/Queso2",Double.parseDouble("15"))
    };

    CheckBox chkBoxGrande;
    CheckBox chkBoxIngred;
    CheckBox chkBoxQueso;
    double extras = 0;
    PrecioPizza precioPizza = null;
    Pizza pizza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Pizzeria");

        precioPizza = new PrecioPizza((TextView) findViewById(R.id.precioFinal));
        final Spinner spinnerPizzas = (Spinner) findViewById(R.id.spinnerPizza);

        PizzaAdapter pizzaAdapter = new PizzaAdapter(this);
        spinnerPizzas.setAdapter(pizzaAdapter);


        spinnerPizzas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pizza = (Pizza) spinnerPizzas.getSelectedItem();
                precioPizza.setPrecioPizza(pizza.getPrecio());
                precioPizza.calculaPrecioPizza();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        chkBoxIngred = (CheckBox) findViewById(R.id.checkBoxIngred);
        chkBoxQueso = (CheckBox) findViewById(R.id.checkBoxQueso);
        chkBoxGrande = (CheckBox) findViewById(R.id.checkBoxGrande);



        chkBoxIngred.setOnCheckedChangeListener(new CheckedChange());

        chkBoxQueso.setOnCheckedChangeListener(new CheckedChange());

        chkBoxGrande.setOnCheckedChangeListener(new CheckedChange());


        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupPedido);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String textoOpcion = "";
                if (group.getCheckedRadioButtonId() == R.id.radioDomicilio) {
                    precioPizza.setDomicilio(1);
                    precioPizza.calculaPrecioPizza();
                }
                if (group.getCheckedRadioButtonId() == R.id.radioLocal) {

                    precioPizza.setDomicilio(0);
                    precioPizza.calculaPrecioPizza();
                }

            }
        });
        final EditText editText = (EditText) findViewById(R.id.numPizza);

        findViewById(R.id.buttonFichaPizzeria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                precioPizza.setCantidadPizzas(Double.parseDouble(editText.getText().toString()));
                precioPizza.calculaPrecioPizza();


                Intent intent = new Intent(MainActivity.this,PantallaResultado.class);

                Bundle bundle = new Bundle();
                bundle.putDouble("precio",pizza.getPrecio());
                bundle.putString("nombre", pizza.getNombre());
                bundle.putDouble("cantidad", precioPizza.getCantidadPizzas());
                bundle.putDouble("extras", precioPizza.getExtras());
                bundle.putDouble("tipoEnvio", precioPizza.getDomicilio());


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

            if (buttonView == chkBoxIngred) {

                if (isChecked) {
                    extras += 1;
                } else {

                    extras -= 1;
                }
            }

            if (buttonView == chkBoxGrande) {
                if (isChecked) {
                    extras += 1;
                } else {

                    extras -= 1;
                }
            }

            if (buttonView == chkBoxQueso) {
                if (isChecked) {
                    extras += 1;
                } else {

                    extras -= 1;
                }
            }

            precioPizza.setExtras(extras);
            precioPizza.calculaPrecioPizza();
        }




    }



    class PizzaAdapter extends ArrayAdapter {
        Activity context;

        public PizzaAdapter(Activity context) {
            super(context, R.layout.spinner_list_pizza, listaPizzas);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {



            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.spinner_list_pizza, null);

            String nombrePizza =  listaPizzas[position].getNombre();
            TextView lblNombrePizza = (TextView)item.findViewById(R.id.nombrePizza);
            lblNombrePizza.setText(nombrePizza);

            TextView lblIngredientesPizza= (TextView)item.findViewById(R.id.ingredientesPizza);
            String ingredientesPizza = listaPizzas[position].getIngredientes();
            lblIngredientesPizza.setText(listaPizzas[position].getIngredientes());

            String precio = listaPizzas[position].getPrecio().toString();
            TextView lblPrecio = (TextView) item.findViewById(R.id.precio);
            lblPrecio.setText(precio+"€");


            ImageView imgView = (ImageView) item.findViewById(R.id.imgPizza);

            if(nombrePizza.equalsIgnoreCase("Margarita")){

                imgView.setImageResource(R.drawable.pizza1);
            }
            if(nombrePizza.equalsIgnoreCase("Barbacoa")){

                imgView.setImageResource(R.drawable.pizza3);
            }
            if(nombrePizza.equalsIgnoreCase("Tres Quesos")){

                imgView.setImageResource(R.drawable.pizza2);
            }


            return(item);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position,convertView,parent);
        }
    }

}
