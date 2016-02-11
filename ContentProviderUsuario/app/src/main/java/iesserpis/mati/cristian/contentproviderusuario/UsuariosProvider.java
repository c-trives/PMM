package iesserpis.mati.cristian.contentproviderusuario;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by mati on 28/01/16.
 */
public class UsuariosProvider extends ContentProvider {
    public final static String AUTHORITY = "iesserpis.mati.cristian.ContentProviderUsuario";

    public static final String ACTIVIDAD = "usuario";
    //Definición del CONTENT_URI
    private static final String uri = "content://" + AUTHORITY + "/" + ACTIVIDAD;
    public final static Uri CONTENT_URI =
            Uri.parse(uri);

    //Clase interna para declarar las columnas
    public static final class Usuarios implements BaseColumns {
        private Usuarios() {}

        //Nombres de columnas
        public static final String COL_USUARIO = "usuario";
        public static final String COL_PASSWORD = "password";
        public static final String COL_EMAIL = "email";
    }

    //Base de datos
    private UsuariosSqliteHelper usudbh;
    private static final String BD_NOMBRE = "DBUsuarios";
    private static final int BD_VERSION = 1;
    private static final String TABLA_USUARIOS = "Usuarios";

    //UriMatcher
    private static final int USUARIOS = 1;
    private static final int USUARIOS_ID = 2;
    private static final UriMatcher uriMatcher;
    //Inicializamos el UriMatcher indicándole el formato de los dos tipos de acceso:
    //genérico a tabla o directo a un registro, indicándole el formato de ambos tipos
    //de URI, de forma que pueda diferenciarlos y devolvernos su tipo.
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ACTIVIDAD, USUARIOS);
        uriMatcher.addURI(AUTHORITY, ACTIVIDAD + "/#", USUARIOS_ID);
    }

    @Override
    public boolean onCreate() {
        usudbh = new UsuariosSqliteHelper(getContext(), BD_NOMBRE, null, BD_VERSION);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] proyeccion, String seleccion,
                        String[] seleccionArgs, String ordenacion) {
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = seleccion;
        if (uriMatcher.match(uri) == USUARIOS_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = usudbh.getWritableDatabase();

        Cursor c = db.query(TABLA_USUARIOS, proyeccion, where, seleccionArgs, null, null, ordenacion);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues valores, String seleccion, String[] seleccionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = seleccion;
        if (uriMatcher.match(uri) == USUARIOS_ID) {
            where = "_id" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = usudbh.getWritableDatabase();

        cont = db.update(TABLA_USUARIOS, valores, where, seleccionArgs);

        return cont;
    }

    @Override
    public int delete(Uri uri, String seleccion, String[] seleccionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = seleccion;
        if (uriMatcher.match(uri) == USUARIOS_ID) {
            where = "_id" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = usudbh.getWritableDatabase();

        cont = db.delete(TABLA_USUARIOS, where, seleccionArgs);

        return cont;
    }

    @Override
    public Uri insert(Uri uri, ContentValues valores) {
        long regId = 1;

        SQLiteDatabase db = usudbh.getWritableDatabase();

        regId = db.insert(TABLA_USUARIOS, null, valores);

        Uri nuevaUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return nuevaUri;
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);



       switch(match) {
            case USUARIOS:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + "/"+ACTIVIDAD;
            case USUARIOS_ID:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + "/"+ACTIVIDAD;
            default:
                return null;
        }
    }
}
