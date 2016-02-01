package iesserpis.mati.cristian.databasetest;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by mati on 11/01/16.
 */
public class ClienteAdapter extends ArrayAdapter {


    Activity context;
    Cliente[] datos;
    ClienteAdapter(Activity context,Cliente[] datos) {
        super(context, R.layout.listitem_titular, datos);
        this.datos = datos;
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        if(item == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.listitem_titular, null);
            TextView lblCodigoCliente = (TextView) item.findViewById(R.id.codigoCliente);
            TextView lblNombreCliente = (TextView) item.findViewById(R.id.nombreCliente);
            TextView lblTlfCliente = (TextView) item.findViewById(R.id.tlfCliente);


            Log.d("tlfCliente", String.valueOf(datos[position].getTelefono()));
            lblCodigoCliente.setText("Codigo: "+String.valueOf(datos[position].getId()));
            lblNombreCliente.setText("Nombre: "+datos[position].getNombre());
            lblTlfCliente.setText("telefono: "+datos[position].getTelefono());
        }
        return item;

    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }
}
