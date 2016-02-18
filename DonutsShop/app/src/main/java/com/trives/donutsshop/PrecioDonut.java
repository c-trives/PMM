package com.trives.donutsshop;

import android.widget.TextView;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by Trives on 15/02/2016.
 */
public class PrecioDonut implements Serializable {

    private static final long serialVersionUID = 1L;
    private double precioGlaseadoDonuts;
    private double precioRellenoDonuts;
    private double domicilio;
    private double extras;
    private double cantidadDonuts = 1;
    private transient TextView textViewPrecioFinal;
    private double precioFinal;

    public PrecioDonut(TextView textViewPrecioFinal){
        this.textViewPrecioFinal = textViewPrecioFinal;

    }


    public void calculaPrecioDonut( ){

        precioFinal = precioGlaseadoDonuts + precioRellenoDonuts;

        if(getExtras() != 0){
            precioFinal += getExtras();

        }

        precioFinal = precioFinal * cantidadDonuts;
        if(getDomicilio() != 0){

            precioFinal += (precioFinal) * 0.10;
        }

        DecimalFormat df2 = new DecimalFormat(".##");
df2.format(precioFinal);


        textViewPrecioFinal.setText(String.valueOf(
                df2.format(precioFinal)));
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

    public double getCantidadDonuts() {
        return cantidadDonuts;
    }

    public void setCantidadDonuts(double cantidadDonuts) {
        this.cantidadDonuts = cantidadDonuts;
    }

    public double getPrecioGlaseadoDonuts() {
        return precioGlaseadoDonuts;
    }

    public void setPrecioGlaseadoDonuts(double precioGlaseadoDonuts) {
        this.precioGlaseadoDonuts = precioGlaseadoDonuts;
    }

    public double getPrecioRellenoDonuts() {
        return precioRellenoDonuts;
    }

    public void setPrecioRellenoDonuts(double precioRellenoDonuts) {
        this.precioRellenoDonuts = precioRellenoDonuts;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }
}
