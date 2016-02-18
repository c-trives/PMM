package com.trives.donutsshop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PantallaResultado extends AppCompatActivity {

    static GlaseadoDonut glaseadoDonut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_resultado);

        final Bundle bundle = getIntent().getExtras();

        TextView lblPrecioFinalPedido= (TextView) findViewById(R.id.precioFinalPedido);

        glaseadoDonut = (GlaseadoDonut) bundle.getSerializable("glaseado");

        PrecioDonut precioDonut = (PrecioDonut) bundle.getSerializable("precio");

        DecimalFormat df2 = new DecimalFormat(".##");
        lblPrecioFinalPedido.setText(String.valueOf(df2.format(precioDonut.getPrecioFinal())) + "€");


    }


    public static class DibujarDonut extends View {

        private float dibujarX;
        private float dibujarY;


        public DibujarDonut(Context context, AttributeSet attrs) {
            super(context, attrs);
        }



        @Override
        protected void onDraw(Canvas canvas) {


            dibujarX = canvas.getWidth() / 2;
            dibujarY = canvas.getHeight()/ 2;
            Paint miPincel = new Paint();
            miPincel.setStyle(Paint.Style.FILL);

            miPincel.setColor(getResources().getColor(R.color.donutBase));
            canvas.drawCircle(dibujarX, dibujarY, 200, miPincel);


            if(glaseadoDonut.getNombre().equalsIgnoreCase("Rosa")){

                miPincel.setColor(getResources().getColor(R.color.glaseadoRosa));
            }else if(glaseadoDonut.getNombre().equalsIgnoreCase("Chocolate")){

                miPincel.setColor(getResources().getColor(R.color.glaseadoChocolate));

            }else{

                miPincel.setColor(getResources().getColor(R.color.glaseadoChocolateBlanco));
            }
            canvas.drawCircle(dibujarX, dibujarY, 150, miPincel);


            miPincel.setColor(getResources().getColor(R.color.colorPrimaryDark));
            canvas.drawCircle(dibujarX, dibujarY, 75, miPincel);

            miPincel.setColor(Color.BLACK);
            miPincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(dibujarX, dibujarY, 75, miPincel);
            canvas.drawCircle(dibujarX, dibujarY, 200, miPincel);
        }
    }

}
