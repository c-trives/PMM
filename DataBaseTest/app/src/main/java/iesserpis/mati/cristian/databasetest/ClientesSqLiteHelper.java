package iesserpis.mati.cristian.databasetest;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mati on 11/01/16.
 */
public class ClientesSqLiteHelper extends SQLiteOpenHelper {

    String cadSQL = "CREATE TABLE Clientes ( codigo Integer, nombre TEXT, telefono TEXT)";



    public ClientesSqLiteHelper(Context contexto, String nombre, CursorFactory almacen, int version){
        super(contexto,nombre,almacen,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(cadSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Clientes");

        db.execSQL(cadSQL);
    }
}
