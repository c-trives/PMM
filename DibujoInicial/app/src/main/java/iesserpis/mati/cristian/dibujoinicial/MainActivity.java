package iesserpis.mati.cristian.dibujoinicial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DibujarCircle dibujarCircle = new DibujarCircle(this);

        setContentView(dibujarCircle);

        dibujarCircle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                dibujarCircle.setDibujarX(event.getX());
                dibujarCircle.setDibujarY(event.getY());

                setContentView(dibujarCircle);
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class DibujarCircle extends View{

        private float dibujarX;
        private float dibujarY;

        public DibujarCircle(Context contexto){
            super(contexto);
        }





        @Override
        protected void onDraw(Canvas canvas) {

            Paint miPincel = new Paint();
            miPincel.setColor(Color.MAGENTA);
            miPincel.setStyle(Paint.Style.STROKE);
            miPincel.setStrokeWidth(20);
            canvas.drawCircle(dibujarX, dibujarY, 200, miPincel);

            /**   miPincel.setColor(Color.RED);
             miPincel.setStyle(Paint.Style.FILL_AND_STROKE);



             for(int i=100; i<=900; i+=800){

             canvas.drawRect(i,100,i+100,200,miPincel);

             }



             miPincel.setColor(Color.GREEN);

             for(int i=100; i<=900; i+=800){

             canvas.drawRect(i,900,i+100,1000,miPincel);
             }

             miPincel.setColor(Color.CYAN);

             canvas.drawPoint(550,550,miPincel);
             */
        }


        public float getDibujarX() {
            return dibujarX;
        }

        public void setDibujarX(float dibujarX) {
            this.dibujarX = dibujarX;
        }

        public float getDibujarY() {
            return dibujarY;
        }

        public void setDibujarY(float dibujarY) {
            this.dibujarY = dibujarY;
        }
    }

}
