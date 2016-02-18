package com.trives.donutsshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Trives on 15/02/2016.
 */
public class SqLiteHelperCenter extends SQLiteOpenHelper {


    String usuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " nombre_usuario   SMALLINT NOT NULL," +
            " password   VARCHAR(255)," +
            " email       VARCHAR(255)" +
            " ) ;";

    String pedidos = "CREATE TABLE IF NOT EXISTS pedidos (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " user_id      INTEGER NOT NULL," +
            "  FOREIGN KEY (user_id) REFERENCES usuarios (id));";

    String donuts = "CREATE TABLE IF NOT EXISTS donuts (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " pedidos_id      INTEGER NOT NULL," +
            " glaseado       VARCHAR(255)," +
            " relleno       VARCHAR(255)," +
            " precio       FLOAT(5,2)," +
            "  FOREIGN KEY (pedidos_id) REFERENCES pedidos(id));";


    public SqLiteHelperCenter(Context contexto, String nombre, SQLiteDatabase.CursorFactory almacen, int version){
        super(contexto,nombre,almacen,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(usuarios);
        db.execSQL(pedidos);
        db.execSQL(donuts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       db.execSQL("DROP TABLE IF EXIST usuarios");
        db.execSQL("DROP TABLE IF EXIST pedidos");
        db.execSQL("DROP TABLE IF EXIST donuts");


        db.execSQL(usuarios);
        db.execSQL(pedidos);
        db.execSQL(donuts);
    }
}
