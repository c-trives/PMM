package com.trives.donutsshop;

/**
 * Created by Trives on 16/02/2016.
 */
public class Donut {

    private int id;
    private String glaseado;
    private String relleno;
    private Double precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGlaseado() {
        return glaseado;
    }

    public void setGlaseado(String glaseado) {
        this.glaseado = glaseado;
    }

    public String getRelleno() {
        return relleno;
    }

    public void setRelleno(String relleno) {
        this.relleno = relleno;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
