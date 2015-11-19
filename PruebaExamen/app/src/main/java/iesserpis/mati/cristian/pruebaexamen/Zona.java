package iesserpis.mati.cristian.pruebaexamen;

/**
 * Created by mati on 9/11/15.
 */
public class Zona {

    private String zona;
    private Double precio;
    private String[] naciones;

    public Zona(String zona,Double precio,String[] naciones){
        this.zona = zona;
        this.precio = precio;
        this.naciones = naciones;

    }
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String[] getNaciones() {
        return naciones;
    }

    public void setNaciones(String[] naciones) {
        this.naciones = naciones;
    }
}
