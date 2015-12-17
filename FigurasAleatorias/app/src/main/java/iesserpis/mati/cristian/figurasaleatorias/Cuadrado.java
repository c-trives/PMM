package iesserpis.mati.cristian.figurasaleatorias;


public class Cuadrado extends Rectangulo
{       
    /** Constructor por defecto: crea un cuadrado de color negro (Color.black) y lado 10.0      */
    public Cuadrado()
    {   super();
        tipo = "cuadrado";
    }
 
    public Cuadrado(int color, double lado) {
        super(color, lado, lado);
        tipo = "cuadrado";
    }
    
    public double getLado() { 
        return base;
    }
        
     public void setLado(double lado) {
        base = altura = lado;
    }
    
    public void setBase(double base) {
        this.base = this.altura = base;
    }
    
    public void setAltura(double altura) {
        this.base = this.altura = altura;
    }
    
    public String toString(){
        return "Cuadrado " + color + " de lado " + getLado();
    }
}
