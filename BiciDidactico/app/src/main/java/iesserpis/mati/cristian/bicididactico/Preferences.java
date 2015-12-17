package iesserpis.mati.cristian.bicididactico;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by mati on 17/12/15.
 */
public class Preferences extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
