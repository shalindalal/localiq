package com.datahack.localiq.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.datahack.localiq.app.dummy.DummyContent;

/**
 * Created by sdalal on 7/12/14.
 */
public class CustomersDB {

    private final Context mContext;

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_AGE = "age";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_WIFI_MAC = "wifi_mac";
    public static final String KEY_BLUETOOTH_UUID = "bluetooth_uuid";
    public static final String KEY_CALLED_AT = "called_at";
    public static final String KEY_VISITED_AT = "visited_at";

    private static final String TAG = "CustomersDB";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "LocalIQ";
    private static final String SQLITE_TABLE = "Customers";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_NAME + "," +
                    KEY_ADDRESS + "," +
                    KEY_AGE + "," +
                    KEY_GENDER + "," +
                    KEY_PHONE_NUMBER + "," +
                    KEY_WIFI_MAC + "," +
                    KEY_BLUETOOTH_UUID + "," +
                    KEY_CALLED_AT + " INTEGER," +
                    KEY_VISITED_AT + " INTEGER," +
                    " UNIQUE (" + KEY_PHONE_NUMBER +"));";
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    //Todo: Make a builder pattern

    public CustomersDB(Context ctx) {
        this.mContext = ctx;
    }

    public CustomersDB open() {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public Cursor listCustomers() {

        Cursor cursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                        KEY_PHONE_NUMBER, KEY_CALLED_AT, KEY_VISITED_AT},
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }

    public void insertCustomer(DummyContent.Customer customer) {
        ContentValues row = new ContentValues();
        row.put(KEY_NAME, customer.name);
        row.put(KEY_ADDRESS, customer.address);
        row.put(KEY_AGE, customer.age);
        row.put(KEY_GENDER, customer.gender);
        row.put(KEY_PHONE_NUMBER, customer.phoneNumber);
        row.put(KEY_WIFI_MAC, customer.wifi_mac);
        row.put(KEY_BLUETOOTH_UUID, customer.bluetooth_uuid);
        mDb.insertWithOnConflict(SQLITE_TABLE, KEY_VISITED_AT, row, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public boolean updateCustomerCall(String phoneNumber) {
        ContentValues row = new ContentValues();
        long time = System.currentTimeMillis() / 1000;
        row.put(KEY_PHONE_NUMBER, phoneNumber);
        row.put(KEY_CALLED_AT, time);
//        mDb.insertWithOnConflict(SQLITE_TABLE, KEY_VISITED_AT, row, SQLiteDatabase.CONFLICT_REPLACE);
        int n = mDb.updateWithOnConflict(SQLITE_TABLE, row, KEY_PHONE_NUMBER + "=\"" + phoneNumber + "\"", null, SQLiteDatabase.CONFLICT_REPLACE);
        if(n > 0) {
            Log.i(TAG, n + " rows updated containing phone number = " + phoneNumber);
            return true;
        }
        Log.i(TAG, n + " rows updated containing phone number = " + phoneNumber);
        return false;
    }

    public boolean updateCustomerVisit(String bluetoothUUID) {
        ContentValues row = new ContentValues();
        long time = System.currentTimeMillis() / 1000;
        row.put(KEY_BLUETOOTH_UUID, bluetoothUUID);
        row.put(KEY_VISITED_AT, time);
//        mDb.insertWithOnConflict(SQLITE_TABLE, KEY_CALLED_AT, row, SQLiteDatabase.CONFLICT_REPLACE);
        int n = mDb.updateWithOnConflict(SQLITE_TABLE, row, KEY_BLUETOOTH_UUID + "=\"" + bluetoothUUID + "\"", null, SQLiteDatabase.CONFLICT_REPLACE);
        if(n > 0) {
            Log.i(TAG, n + " rows updated containing phone number = " + bluetoothUUID);
            return true;
        }
        Log.i(TAG, n + " rows updated containing phone number = " + bluetoothUUID);
        return false;
    }

}
