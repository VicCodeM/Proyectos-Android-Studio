package com.android.recetarioaleatrio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipes.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPES_TABLE = "CREATE TABLE recipes (id INTEGER PRIMARY KEY, name TEXT, preparation TEXT, image BLOB)";
        db.execSQL(CREATE_RECIPES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recipes");
        onCreate(db);
    }

    public Recipe getRandomRecipe() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipes ORDER BY RANDOM() LIMIT 1", null);
        Recipe recipe = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String preparation = cursor.getString(cursor.getColumnIndex("preparation"));
            byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
            recipe = new Recipe(id, name, preparation, image);
        }
        cursor.close();
        return recipe;
    }

    public long insertRecipe(String name, String preparation, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("preparation", preparation);
        values.put("image", image);

        long newRowId = db.insert("recipes", null, values);
        db.close();
        return newRowId;
    }
}
