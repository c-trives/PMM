package iesserpis.mati.cristian.ctrivesexamen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mati on 9/11/15.
 */
public class PantallaResultado extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_resultado_layout);

        TextView textViewNombrePizza = (TextView) findViewById(R.id.fichaPizza);
        TextView textViewPrecioBasePizza = (TextView) findViewById(R.id.fichaPrecioBase);
        TextView textViewPrecioExtraPizza = (TextView) findViewById(R.id.fichaPrecioExtras);
        TextView textViewUnidadesPizza = (TextView) findViewById(R.id.fichaCantidadUnidades);
        TextView textViewDomicilioPizza = (TextView) findViewById(R.id.fichaEnvio);
        TextView textViewCosteFinalPizza = (TextView) findViewById(R.id.fichaCosteFinal);
        ImageView imageViewPizza = (ImageView) findViewById(R.id.fichaPizzaImagen);

        final Bundle bundle = getIntent().getExtras();
        String nombrePizza = bundle.getString("nombre");
        double precioBase = bundle.getDouble("precio");
        double precioExtras = bundle.getDouble("extras");
        double unidades = bundle.getDouble("cantidad");
        double envio = bundle.getDouble("tipoEnvio");
        textViewNombrePizza.setText("Pizza: "+ nombrePizza);
        textViewPrecioBasePizza.setText("Precio Base: "+ String.valueOf(precioBase));
        textViewPrecioExtraPizza.setText("Extra: " + String.valueOf(precioExtras));
        textViewUnidadesPizza.setText("Unidades: " + String.valueOf(unidades));

        if(envio == 1){

            textViewDomicilioPizza.setText("Envio: Domicilio");
        }else{
            textViewDomicilioPizza.setText("Envio: Local");
        }

        Double precioFinal = ((precioBase + precioExtras)* unidades);

        if(envio == 1){
            precioFinal += precioFinal * 0.1;
        }
    textViewCosteFinalPizza.setText("COSTE FINAL: "+ precioFinal.toString());


        if(nombrePizza.equalsIgnoreCase("Margarita")){

            imageViewPizza.setImageResource(R.drawable.pizza1);
        }
        if(nombrePizza.equalsIgnoreCase("Barbacoa")){

            imageViewPizza.setImageResource(R.drawable.pizza3);
        }
        if(nombrePizza.equalsIgnoreCase("Tres Quesos")){

            imageViewPizza.setImageResource(R.drawable.pizza2);
        }

    }


}
