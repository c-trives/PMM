package iesserpis.mati.cristian.ctrivesexamen;

/**
 * Created by mati on 9/11/15.
 */
public class Pizza {

    private String nombre;
    private String ingredientes;
    private Double precio;

    public Pizza(String nombre,String ingredientes,Double precio){

        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
