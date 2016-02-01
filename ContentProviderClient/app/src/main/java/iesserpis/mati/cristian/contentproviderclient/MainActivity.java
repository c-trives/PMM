package iesserpis.mati.cristian.contentproviderclient;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button btnInsertar;
    private Button btnConsultar;
    private Button btnEliminar;
    private Button btnLlamadas;
    private TextView txtResultados;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias a los controles
        txtResultados = (TextView)findViewById(R.id.TxtResultados);
        btnConsultar = (Button)findViewById(R.id.BtnConsultar);
        btnInsertar = (Button)findViewById(R.id.BtnInsertar);
        btnEliminar = (Button)findViewById(R.id.BtnEliminar);
        btnLlamadas = (Button)findViewById(R.id.BtnLlamadas);

        btnConsultar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Columnas de la tabla a recuperar
                String[] proyeccion = new String[] {
                        "_id",
                        "usuario",
                        "password",
                        "email" };

                //Incorporamos el uri al que queremos acceder
                String uri = "content://iesserpis.mati.cristian.ContentProviderUsuario/usuario";
                Uri usuariosUri =  Uri.parse(uri);
                Log.e("ContentProvider2", "LLEGAMOS");
                //Obtenemos una referencia al content provider al que queremos acceder
                ContentResolver cr = getContentResolver();

                //Hacemos la consulta
                Cursor cur = cr.query(usuariosUri,
                        proyeccion, //Columnas a devolver
                        null,       //Condición de la query
                        null,       //Argumentos variables de la query
                        null);      //Orden de los resultados

                if (cur.moveToFirst())
                {
                    String usuario;
                    String password;
                    String email;

                    int colUsuario = cur.getColumnIndex("usuario");
                    int colPassword = cur.getColumnIndex("password");
                    int colEmail = cur.getColumnIndex("email");

                    txtResultados.setText("");

                    do
                    {

                        usuario = cur.getString(colUsuario);
                        password = cur.getString(colPassword);
                        email = cur.getString(colEmail);

                        txtResultados.append(usuario + " - " + password + " - " + email + "\n");

                    } while (cur.moveToNext());
                }
            }
        });

        btnInsertar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Creamos los datos de un usuario nuevo
                ContentValues values = new ContentValues();
                values.put("usuario", "UsuarioN");
                values.put("password", "PasswordXXX");
                values.put("email", "nuevo@cefire.com");

                //Incorporamos el uri al que queremos acceder
                String uri = "content://iesserpis.mati.cristian.ContentProviderUsuario/usuario";
                Uri usuariosUri =  Uri.parse(uri);
                //Obtenemos una referencia al content provider al que queremos acceder
                ContentResolver cr = getContentResolver();

                //Insertamos un usuario nuevo
                cr.insert(usuariosUri, values);
            }
        });

        btnEliminar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Incorporamos el uri al que queremos acceder
                String uri = "content://iesserpis.mati.cristian.ContentProviderUsuario/usuario";
                Uri usuariosUri =  Uri.parse(uri);
                //Obtenemos una referencia al content provider al que queremos acceder
                ContentResolver cr = getContentResolver();

                //Eliminamos el usuario introducido con el botón de Insertar
                cr.delete(usuariosUri, "usuario" + " = 'UsuarioN'", null);
            }
        });

        btnLlamadas.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Columnas que queremos recuperar
                String[] proyeccion = new String[] {
                        Calls.TYPE,
                        Calls.NUMBER };

                //Uri a la que queremos acceder. En este caso es la del historial de llamadas
                Uri llamadasUri =  Calls.CONTENT_URI;
                //Referencia al content provider al que queremos acceder
                ContentResolver cr = getContentResolver();

                Cursor cur = cr.query(llamadasUri,
                        proyeccion, //Columnas a devolver
                        null,       //Condición de la query
                        null,       //Argumentos variables de la query
                        null);      //Orden de los resultados


                if (cur.moveToFirst())
                {
                    int tipo;
                    String tipoLlamada = "";
                    String telefono;

                    int colTipo = cur.getColumnIndex(Calls.TYPE);
                    int colTelefono = cur.getColumnIndex(Calls.NUMBER);

                    txtResultados.setText("");

                    do
                    {

                        tipo = cur.getInt(colTipo);
                        telefono = cur.getString(colTelefono);

                        if(tipo == Calls.INCOMING_TYPE)
                            tipoLlamada = "ENTRADA";
                        else if(tipo == Calls.OUTGOING_TYPE)
                            tipoLlamada = "SALIDA";
                        else if(tipo == Calls.MISSED_TYPE)
                            tipoLlamada = "PERDIDA";

                        txtResultados.append(tipoLlamada + " - " + telefono + "\n");

                    } while (cur.moveToNext());
                }
            }
        });
    }
}
