package com.example.myprojectfinal.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "healthcare.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public Database(Context context, String healthcare, String s, int i) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "CREATE TABLE " + TABLE_USERS +
                " (" + COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create a new table
        onCreate(db);
    }

    public void register(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);

        // Insert the user data into the users table
        try {
            db.insertOrThrow(TABLE_USERS, null, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public boolean login(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the selection arguments
        String selection = COLUMN_NAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {name, password};

        // Query the users table with the selection arguments
        Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows returned
        boolean success = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return success;
    }
}

