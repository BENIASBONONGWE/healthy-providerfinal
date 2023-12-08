package com.example.myprojectfinal.Databases;// ... (Your existing imports)

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookingDatabaseMalaria extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "booking_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BOOKINGS = "bookings";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE_TIME = "date_time";

    public BookingDatabaseMalaria(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_BOOKINGS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DATE_TIME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table and recreate it if the database version is updated
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        onCreate(db);
    }

    // Method to insert a new booking into the database
    public long insertBooking(String dateTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE_TIME, dateTime);
        long id = db.insert(TABLE_BOOKINGS, null, values);
        db.close();
        return id;
    }

    // Method to check if there is a recent booking within the specified time window
    public boolean hasRecentBooking(String dateTime, long timeWindow) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_DATE_TIME + " = ?";
        String[] selectionArgs = {dateTime};
        Cursor cursor = db.query(TABLE_BOOKINGS, columns, selection, selectionArgs, null, null, null);

        // Check if there is a booking with the same dateTime
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }

        // If no exact match, check for bookings within the time window
        String[] projection = {COLUMN_ID};
        String selectionTimeWindow = COLUMN_DATE_TIME + " BETWEEN ? AND ?";
        String[] selectionArgsTimeWindow = {String.valueOf(getStartTime(timeWindow)), String.valueOf(getEndTime())};
        Cursor timeWindowCursor = db.query(TABLE_BOOKINGS, projection, selectionTimeWindow, selectionArgsTimeWindow, null, null, null);

        boolean hasRecentBooking = timeWindowCursor.moveToFirst();

        cursor.close();
        timeWindowCursor.close();
        db.close();

        return hasRecentBooking;
    }

    private long getStartTime(long timeWindow) {
        return System.currentTimeMillis() - timeWindow;
    }

    private long getEndTime() {
        return System.currentTimeMillis();
    }
}
