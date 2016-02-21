package com.example.ali.nativx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ali on 2/20/2016.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="UserDatabase";

    public DatabaseManager(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override

    public void onCreate(SQLiteDatabase database) {

        database.execSQL("CREATE TABLE User (email TEXT, firstName TEXT, lastName TEXT, password TEXT);");

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS User");

        onCreate(db);

    }

    public void addUser(String first_name, String last_name, String email, String password){
        ContentValues values=new ContentValues();

        values.put("email", email);
        values.put("firstName", first_name);
        values.put("lastName", last_name);
        values.put("password", password);

        getWritableDatabase().insert("User", null, values);
    }

    public boolean isUserExist(String email){
        //String selectQuery = "SELECT firstName FROM User WHERE email = ?", new String[]{ email};

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM User WHERE email = '" + email+"';", null);
            System.out.println("***********************" + cursor.getCount());
           // System.out.println(cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2));

            // looping through all rows and adding to list
            if (cursor.getCount() < 1) {
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Exception .... "+ e);
        }
        return true;
    }
}
