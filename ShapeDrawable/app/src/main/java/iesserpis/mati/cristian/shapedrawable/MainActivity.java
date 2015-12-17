package iesserpis.mati.cristian.shapedrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    public static class DibujarFormas extends View {

        private ShapeDrawable shapeDrawable;

        public DibujarFormas(Context context, AttributeSet attrs) {
            super(context, attrs);
            int x=10,y=10;
            int ancho=500,alto=200;

            shapeDrawable = new ShapeDrawable(new ArcShape(-60,120));
            shapeDrawable.getPaint().setColor(Color.CYAN);
            shapeDrawable.setBounds(x, y, x + ancho, y + alto);
        }



        @Override
        protected void onDraw(Canvas canvas) {
            shapeDrawable.draw(canvas);
        }
    }
}
