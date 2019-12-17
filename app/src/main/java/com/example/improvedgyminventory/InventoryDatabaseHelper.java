package com.example.improvedgyminventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class InventoryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";
    private static final String TABLE_NAME = "inventory";
    private static int DATABASE_VERSION = 1;

    private static final String COLUMN_ITEMKEY = "item_key";
    private static final String COLUMN_ITEMNAME = "item_name";
    private static final String COLUMN_ITEMCOUNT = "item_count";



    public InventoryDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ITEMKEY + " INTEGER PRIMARY KEY, " +
                COLUMN_ITEMNAME + " TEXT, " +
                COLUMN_ITEMCOUNT + " INTEGER)";

        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DATABASE_VERSION = newVersion;
        String updateQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(updateQuery);
        onCreate(db);
    }

    public void insertItem(GymItem item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEMNAME, item.itemName);
        contentValues.put(COLUMN_ITEMCOUNT, item.count);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updateItem(GymItem item) {
        String where = COLUMN_ITEMNAME + "=\"" + item.itemName + "\"";
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEMCOUNT, item.count);

        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, contentValues, where, null);
        db.close();
    }

    public ArrayList<GymItem> getItems() {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {COLUMN_ITEMNAME, COLUMN_ITEMCOUNT};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        ArrayList<GymItem> items = new ArrayList<>();

        int nameIndex = cursor.getColumnIndex(COLUMN_ITEMNAME);
        int countIndex = cursor.getColumnIndex(COLUMN_ITEMCOUNT);

        while (cursor.moveToNext()) {
            GymItem item = new GymItem(cursor.getString(nameIndex), cursor.getInt(countIndex));
            items.add(item);
        }

        cursor.close();
        return items;
    }
}
