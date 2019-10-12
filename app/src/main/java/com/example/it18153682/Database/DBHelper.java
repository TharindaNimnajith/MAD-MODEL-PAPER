package com.example.it18153682.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserProfile.db";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                        UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                        UserProfile.Users.COL1 + " TEXT," +
                        UserProfile.Users.COL2 + " TEXT," +
                        UserProfile.Users.COL3 + " TEXT," +
                        UserProfile.Users.COL4 + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);


    }

    public void addInfo(String UserName , String DOB , String Gender, String Password){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COL1,UserName);
        values.put(UserProfile.Users.COL2,DOB);
        values.put(UserProfile.Users.COL3,Gender);
        values.put(UserProfile.Users.COL4,Password);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);

    }

    public boolean  updateInfor(String id , String UserName , String DOB , String Gender, String Password ) {


        SQLiteDatabase db = this.getWritableDatabase();

    // New value for one column

        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COL1, UserName);
        values.put(UserProfile.Users.COL2, DOB);
        values.put(UserProfile.Users.COL3, Gender);
        values.put(UserProfile.Users.COL4, Password);


    // Which row to update, based on the title
        String selection = UserProfile.Users._ID + " LIKE ?";
        String[] selectionArgs = {id};

        int count = db.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count > 0){
            return true;
        }

        else {
            return false;
        }

    }

    public Cursor readAllInfor(){

        SQLiteDatabase db = this.getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COL1,
                UserProfile.Users.COL2,
                UserProfile.Users.COL3,
                UserProfile.Users.COL4
        };

// Filter results WHERE "title" = 'My Title'
//        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        return cursor;

    }

    public Cursor readAllInfor(String id){

        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COL1,
                UserProfile.Users.COL2,
                UserProfile.Users.COL3,
                UserProfile.Users.COL4
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users._ID + " = ?";
        String[] selectionArgs = { id };

// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        return cursor;

    }

    public void delete(String id){

        SQLiteDatabase db = this.getWritableDatabase();


        // Define 'where' part of query.
        String selection = UserProfile.Users._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { id };
        // Issue SQL statement.
        int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
    }

}
