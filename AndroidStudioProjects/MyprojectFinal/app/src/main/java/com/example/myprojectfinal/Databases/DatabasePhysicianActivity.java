package com.example.myprojectfinal.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class DatabasePhysicianActivity extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "booking_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKINGS = "bookings";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE_TIME = "date_time";

    public DatabasePhysicianActivity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_BOOKINGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE_TIME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        onCreate(db);
    }

    public long insertBooking(String dateTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE_TIME, dateTime);
        long id = db.insert(TABLE_BOOKINGS, null, values);
        db.close();
        return id;
    }

    public boolean hasRecentBooking(String dateTime, long expirationTime) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_DATE_TIME};
        String selection = COLUMN_DATE_TIME + " = ?";
        String[] selectionArgs = {dateTime};

        Cursor cursor = db.query(
                TABLE_BOOKINGS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean hasRecentBooking = false;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(COLUMN_DATE_TIME);
                if (columnIndex != -1) {
                    long storedTimeInMillis = getTimeInMillis(cursor.getString(columnIndex));
                    long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
                    hasRecentBooking = (currentTimeInMillis - storedTimeInMillis) < expirationTime;
                }
            }
            cursor.close();
        }

        db.close();
        return hasRecentBooking;
    }


    private long getTimeInMillis(String dateTime) {
        // Assuming the date-time format is "day/month/year hour:minute"
        String[] dateTimeParts = dateTime.split(" ");
        String[] dateParts = dateTimeParts[0].split("/");
        String[] timeParts = dateTimeParts[1].split(":");
        int year = Integer.parseInt(dateParts[2]);
        int month = Integer.parseInt(dateParts[1]) - 1; // Months are 0-based in Calendar
        int day = Integer.parseInt(dateParts[0]);
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);

        return calendar.getTimeInMillis();
    }
}
