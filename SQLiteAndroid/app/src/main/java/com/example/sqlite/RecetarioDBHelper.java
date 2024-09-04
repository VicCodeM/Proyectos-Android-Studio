package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecetarioDBHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "recetario.db";

    // Versión de la base de datos
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla de recetas
    private static final String TABLE_RECETAS = "recetas";

    // Columnas de la tabla de recetas
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_INGREDIENTES = "ingredientes";
    private static final String COLUMN_PREPARACION = "preparacion";

    // Sentencia SQL para crear la tabla de recetas
    private static final String CREATE_TABLE_RECETAS = "CREATE TABLE " + TABLE_RECETAS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOMBRE + " TEXT NOT NULL, " +
            COLUMN_INGREDIENTES + " TEXT, " +
            COLUMN_PREPARACION + " TEXT)";

    public RecetarioDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RECETAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si la versión antigua es 1 y la nueva versión es 2,
        // entonces necesitamos actualizar la base de datos.
        if (oldVersion == 1 && newVersion == 2) {
            // Agregar una nueva columna a la tabla de recetas
            String ALTER_TABLE_ADD_COLUMN = "ALTER TABLE " + TABLE_RECETAS + " ADD COLUMN " +
                    "nueva_columna TEXT";
            db.execSQL(ALTER_TABLE_ADD_COLUMN);
        }
        // Puedes agregar más condiciones para manejar diferentes actualizaciones
    }


    // Insertar una receta
    public long insertarReceta(String nombre, String ingredientes, String preparacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_INGREDIENTES, ingredientes);
        values.put(COLUMN_PREPARACION, preparacion);
        return db.insert(TABLE_RECETAS, null, values);
    }

    // Actualizar una receta
    public int actualizarReceta(int id, String nombre, String ingredientes, String preparacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_INGREDIENTES, ingredientes);
        values.put(COLUMN_PREPARACION, preparacion);
        return db.update(TABLE_RECETAS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Eliminar una receta
    public void eliminarReceta(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECETAS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Consultar todas las recetas
    public Cursor consultarRecetas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RECETAS, null, null, null, null, null, null);
    }

    // Consultar una receta por ID
    public Cursor consultarRecetaPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RECETAS, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
    }

    public Cursor buscarRecetas(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NOMBRE, COLUMN_INGREDIENTES, COLUMN_PREPARACION};
        String selection = COLUMN_NOMBRE + " LIKE? OR " + COLUMN_INGREDIENTES + " LIKE? OR " + COLUMN_PREPARACION + " LIKE?";
        String[] selectionArgs = {"%" + query + "%", "%" + query + "%", "%" + query + "%"};
        return db.query(TABLE_RECETAS, columns, selection, selectionArgs, null, null, null);
    }


}
