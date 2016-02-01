package iesserpis.mati.cristian.profesorado;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by mati on 25/01/16.
 */
public class CentroAdapter extends ArrayAdapter{


        Activity context;
        Centro[] datos;


        CentroAdapter(Activity context,Centro[] datos) {
        super(context, R.layout.spinner_centro_formulario, datos);
        this.datos = datos;
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        if(item == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.spinner_centro_formulario, null);
            TextView lblNombreCentro = (TextView) item.findViewById(R.id.nombreCentroSpinnerFormulario);
            TextView lblDireccionCentro = (TextView) item.findViewById(R.id.direccionCentroSpinnerFormulario);
            TextView lblTlfCentro = (TextView) item.findViewById(R.id.telefonoCentroSpinnerFormulario);
            TextView lblCodCentro = (TextView) item.findViewById(R.id.codCentroSpinnerFormulario);


            lblCodCentro.setText("CodigoCentro: "+String.valueOf(datos[position].getCod_centro()));
            lblNombreCentro.setText("Nombre: "+String.valueOf(datos[position].getNombre()));
            lblDireccionCentro.setText("Direccion: "+datos[position].getDireccion());
            lblTlfCentro.setText("telefono: "+datos[position].getTelefono());
        }
        return item;

    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}
