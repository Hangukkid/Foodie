package com.example.hangukkid.foodie.sqlite3;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.example.hangukkid.foodie.sqlite3.IngredientsContract.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFunctions extends AppCompatActivity{
/*    IngredientsDbHelper mDbHelper = new IngredientsDbHelper(this);
    SQLiteDatabase db = mDbHelper.getWritableDatabase();*/

    public static long add_ingredient(SQLiteDatabase db, String ingredient_name, Long ingredient_amount, String ingredient_unit){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Ingredients.COLUMN_NAME_INGREDIENT, ingredient_name);
        values.put(Ingredients.COLUMN_NAME_AMOUNT, ingredient_amount);
        values.put(Ingredients.COLUMN_NAME_UNIT, ingredient_unit);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Ingredients.TABLE_NAME, null, values);
        return newRowId;
    }

    public static int remove_ingredient(SQLiteDatabase db, String ingredient_name){
        // Define 'where' part of query.
        String selection = Ingredients.COLUMN_NAME_INGREDIENT + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { ingredient_name };
        // Issue SQL statement.
        int deletedRows = db.delete(Ingredients.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }

    public static List all_ingredients(SQLiteDatabase db){
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Ingredients.COLUMN_NAME_INGREDIENT,
                Ingredients.COLUMN_NAME_AMOUNT,
                Ingredients.COLUMN_NAME_UNIT
        };

        Cursor cursor = db.query(
                Ingredients.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List itemNames = new ArrayList<>();

        while(cursor.moveToNext()) {
            String itemName = cursor.getString(
                    cursor.getColumnIndexOrThrow(Ingredients.COLUMN_NAME_INGREDIENT));
            itemNames.add(itemName);
        }
        cursor.close();

        return itemNames;
    }

    public static Long get_ingredient_amount(SQLiteDatabase db, String ingredient){
        String[] projection = {
                BaseColumns._ID,
                Ingredients.COLUMN_NAME_INGREDIENT,
                Ingredients.COLUMN_NAME_AMOUNT,
                Ingredients.COLUMN_NAME_UNIT
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Ingredients.COLUMN_NAME_INGREDIENT + " = ?";
        String[] selectionArgs = { ingredient };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Ingredients.COLUMN_NAME_AMOUNT + " DESC";

        Cursor cursor = db.query(
                Ingredients.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        Long foundIngredient = 0L;
        if(cursor.moveToNext()) {
            Long itemAmount = cursor.getLong(
                    cursor.getColumnIndexOrThrow(Ingredients.COLUMN_NAME_AMOUNT));
            foundIngredient = itemAmount;
        }
        cursor.close();
        return foundIngredient;
    }

    public static int update_amount(SQLiteDatabase db, String ingredient_name, String ingredient_amount){
        ContentValues values = new ContentValues();
        values.put(Ingredients.COLUMN_NAME_AMOUNT, ingredient_amount);

        String selection = Ingredients.COLUMN_NAME_INGREDIENT + " LIKE ?";
        String[] selectionArgs = {ingredient_name};

        int count = db.update(
                Ingredients.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

}
