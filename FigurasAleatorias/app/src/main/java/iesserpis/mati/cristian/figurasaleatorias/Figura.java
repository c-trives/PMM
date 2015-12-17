package iesserpis.mati.cristian.figurasaleatorias;

import android.graphics.Color;

public class Figura
{
    protected int color;
    protected String tipo;

    /** Constructor por defecto: crea una figura por defecto de color negro (Color.black) y tipo "indefinido" */
    public Figura() {
        color = Color.BLACK;
        tipo = "indefinido";
    }
    
    public Figura(int color, String tipo) {
        this.color = color;
        this.tipo = tipo;
    }
    
    public String getTipo() { 
        return tipo;
    }
    
    public int getColor() {
        return color;
    }
    
    public void setColor(int color){
        this.color = color;
    }
    
    public String toString(){
        return "Figura de tipo: " + tipo + " y color " + color;
    }
        
    /** Devuelve el true si la figura actual es igual a la que recibe como par�metro.
     *  Se considera que dos figuras son iguales si tienen el mismo tipo y color */
    public boolean equals(Object x) {
        Figura f = (Figura) x;


        return (color ==  f.color && tipo.equals(f.tipo));
    }
}