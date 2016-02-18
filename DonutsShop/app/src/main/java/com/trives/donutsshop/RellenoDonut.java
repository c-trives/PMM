package com.trives.donutsshop;

import java.io.Serializable;

/**
 * Created by Trives on 15/02/2016.
 */
public class RellenoDonut implements Serializable {

    private String nombre;
    private Double precio;
    private static final long serialVersionUID = 1L;

    public RellenoDonut(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
