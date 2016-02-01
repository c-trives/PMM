package iesserpis.mati.cristian.profesorado;

import java.io.Serializable;

/**
 * Created by mati on 18/01/16.
 */
public class Profesor implements Serializable {


    private static final long serialVersionUID = 1L;

    private int codigoCentro;
    private int dni;
    private String apellidos;
    private String especialidad;

    public Profesor() {
    }

    public Profesor(int codigoCentro, int dni, String apellidos, String especialidad) {
        this.codigoCentro = codigoCentro;
        this.dni = dni;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
    }


    public int getCodigoCentro() {
        return codigoCentro;
    }

    public void setCodigoCentro(int codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
