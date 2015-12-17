package iesserpis.mati.cristian.pruebapreferencias;


import android.os.Bundle;
import android.preference.PreferenceActivity;
public class PantallaOpciones extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);
    }
}