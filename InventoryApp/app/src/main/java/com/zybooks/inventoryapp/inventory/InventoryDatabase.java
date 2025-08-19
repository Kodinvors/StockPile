package com.zybooks.inventoryapp.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class InventoryDatabase extends SQLiteOpenHelper {

    private final Context context;
    private static final  String DATABASE_NAME = "Inventory.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ITEM = "item_name";
    private static final String COLUMN_ITEM_QUANTITY = "item_quantity";

    public InventoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase inventoryDB) {
        inventoryDB.execSQL("create Table my_library(item_name TEXT primary key, quantity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase inventoryDB, int oldVersion, int newVersion) {
        inventoryDB.execSQL("DROP TABLE IF EXISTS my_library");
        onCreate(inventoryDB);
    }

    void addItem(String itemName) {
        SQLiteDatabase inventoryDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ITEM, itemName);
        contentValues.put(COLUMN_ITEM_QUANTITY, "0");

        long result = inventoryDB.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void updateQuantity(String row_id, String itemName, String itemQuantity) {
        SQLiteDatabase inventoryDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ITEM, itemName);
        contentValues.put(COLUMN_ITEM_QUANTITY, itemQuantity);

        long result = inventoryDB.update(TABLE_NAME, contentValues, " _id=?", new String[] {row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) {

        SQLiteDatabase db = this.getReadableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[] {row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
