package iesserpis.mati.cristian.difformaseventos;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by mati on 29/10/15.
 */
public class MiButton extends Button implements OnClickListener{
   Context ctx = null;

    public MiButton(Context context){
        super(context);
        ctx= context;
        this.setOnClickListener(this);
    }

    public MiButton(Context context, AttributeSet attr){
        super(context,attr);
        ctx=context;
        this.setOnClickListener(this);
    }

    public MiButton(Context context, AttributeSet attr, int defaultStyles){
        super(context, attr, defaultStyles);
        ctx=context;
        this.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        Toast.makeText(ctx, "Pulsado boton 1", Toast.LENGTH_SHORT).show();
    }



}