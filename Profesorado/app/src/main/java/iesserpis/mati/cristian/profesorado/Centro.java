package iesserpis.mati.cristian.profesorado;

import java.io.Serializable;

/**
 * Created by mati on 18/01/16.
 */
public class Centro  implements Serializable {

    private int cod_centro;
    private String tipo_dentro;
    private String nombre;
    private String direccion;
    private String telefono;
    private int num_Plazas;

    public Centro() {
    }

    public Centro(int cod_centro, String tipo_dentro, String nombre, String direccion, String telefono, int num_Plazas) {
        this.cod_centro = cod_centro;
        this.tipo_dentro = tipo_dentro;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.num_Plazas = num_Plazas;
    }


    public int getCod_centro() {
        return cod_centro;
    }

    public void setCod_centro(int cod_centro) {
        this.cod_centro = cod_centro;
    }

    public String getTipo_dentro() {
        return tipo_dentro;
    }

    public void setTipo_dentro(String tipo_dentro) {
        this.tipo_dentro = tipo_dentro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNum_Plazas() {
        return num_Plazas;
    }

    public void setNum_Plazas(int num_Plazas) {
        this.num_Plazas = num_Plazas;
    }
}
