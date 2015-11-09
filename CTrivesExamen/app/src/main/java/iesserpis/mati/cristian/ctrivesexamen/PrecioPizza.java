package iesserpis.mati.cristian.ctrivesexamen;

import android.widget.TextView;

/**
 * Created by mati on 9/11/15.
 */
public class PrecioPizza {

    private double precioPizza;
    private double domicilio;
    private double extras;
    private double cantidadPizzas = 1;
    private TextView textViewPrecioFinal;

    public PrecioPizza(TextView textViewPrecioFinal){
        this.textViewPrecioFinal = textViewPrecioFinal;

    }


    public void calculaPrecioPizza( ){




        double precioFinal = precioPizza;

        if(getExtras() != 0){
            precioFinal += getExtras();

        }

        precioFinal = precioFinal * cantidadPizzas;
        if(getDomicilio() != 0){

            precioFinal += (precioFinal) * 0.10;
        }

        textViewPrecioFinal.setText(String.valueOf(precioFinal));
    }


    public double getPrecioPizza() {
        return precioPizza;
    }

    public void setPrecioPizza(double precioPizza) {
        this.precioPizza = precioPizza;
    }

    public double getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(double domicilio) {
        this.domicilio = domicilio;
    }

    public double getExtras() {
        return extras;
    }

    public void setExtras(double extras) {
        this.extras = extras;
    }

    public double getCantidadPizzas() {
        return cantidadPizzas;
    }

    public void setCantidadPizzas(double cantidadPizzas) {
        this.cantidadPizzas = cantidadPizzas;
    }
}
