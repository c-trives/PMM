package iesserpis.mati.cristian.figurasaleatorias;

import android.graphics.Color;

public class Rectangulo extends Figura
{
    protected double base;
    protected double altura;
    
    /** Constructor por defecto: crea un rect�ngulo de color negro (Color.black), base 10.0 y altura 10.0   */
    public Rectangulo() {
        super(Color.BLACK, "rect�ngulo");
        base = 10.0;
        altura = 10.0;
    }
   
    public Rectangulo(int color, double base, double altura) {
        super(color, "rect�ngulo");
        this.base = base;
        this.altura = altura;
    }
    
    public double getBase() { 
        return base;
    }
    
    public double getAltura() { 
        return altura;
    }
    
    public void setBase(double base) {
        this.base = base;
    }
    
    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double area(){
        return base * altura;
    }
    
    public String toString() {
        return "Rect�ngulo " + color + " de base " + base + " y altura " + altura;
    }

 }