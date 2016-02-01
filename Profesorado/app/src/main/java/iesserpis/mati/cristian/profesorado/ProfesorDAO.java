package iesserpis.mati.cristian.profesorado;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mati on 18/01/16.
 */
public class ProfesorDAO {

    private Context contexto;
    SQLiteDatabase db ;

    public ProfesorDAO(Context contexto,SQLiteDatabase db) {
        this.contexto = contexto;
        this.db = db;
    }


    public Profesor insertarProfesor(Profesor profesor){

        String query = "insert into profesores values ('"+profesor.getCodigoCentro()+"','"+profesor.getDni()+"','"+ profesor.getApellidos()  +"','"+ profesor.getEspecialidad()+"')";
       db.execSQL(query);



        return profesor;
    }

    public Profesor editarProfesor(Profesor profesor){

        String query = "UPDATE profesores SET apellidos='"+profesor.getApellidos()+"', especialidad='"+profesor.getEspecialidad()+"', cod_centro='"+profesor.getCodigoCentro()+"'"+
                " where dni=" + profesor.getDni();
        db.execSQL(query);



        return profesor;
    }


    public String deleteProfesor(Profesor profesor){
        String query = "DELETE from profesores where dni='"+profesor.getDni()+"'";
        db.execSQL(query);

        return "Profesor de dni :" + profesor.getDni() + " eliminado";
    }


}
