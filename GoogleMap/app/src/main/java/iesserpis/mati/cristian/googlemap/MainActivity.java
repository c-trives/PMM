package iesserpis.mati.cristian.googlemap;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private EditText longitud;
    private EditText latitud;
    private Button button;
    private TextView texto;
    String direccion = "";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        longitud = (EditText) findViewById(R.id.longitud);
        latitud = (EditText) findViewById(R.id.latitud);
        button = (Button) findViewById(R.id.boton);
        texto = (TextView) findViewById(R.id.texto);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String longitudCad = longitud.getText().toString();
                    String latitudCad = latitud.getText().toString();


                    final String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitudCad + "," + longitudCad + "&sensor=false";
                    new TareaHttpJsonAsincrona().execute(url);
                }catch (Exception e){
                    texto.append("Error al conectar\n");
                    e.printStackTrace();
                }

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
    public class TareaHttpJsonAsincrona extends AsyncTask <String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream;
            HttpURLConnection httpURLConnection;
            Integer res = 0;
            try {
                URL url = new URL(params[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setRequestMethod("GET");
                int statusCode = httpURLConnection.getResponseCode();

                if (statusCode ==  200) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    String result = InputStreamString(inputStream);
                    parseaJson(result);
                    //correcta
                    res = 1;
                }
                else
                    //incorrecta
                    res = 0;
            }
            catch (Exception e) {
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
            return res;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if(result == 1)
                texto.setText(direccion);
            else
                Toast.makeText(getApplicationContext(), "Error en obtener los datos!", Toast.LENGTH_SHORT).show();
        }
    }
    private String InputStreamString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String aux1;
        String aux2 = "";

        while((aux1 = bufferedReader.readLine()) != null)
            aux2 += aux1;

        inputStream.close();

        return aux2;
    }


    private void parseaJson(String json) {
        try {
            JSONObject reis = new JSONObject(json);
            JSONArray lugares = reis.optJSONArray("results");
            direccion = lugares.getJSONObject(0).getString("formatted_address");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

