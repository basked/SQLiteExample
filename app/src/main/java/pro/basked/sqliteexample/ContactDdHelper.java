package pro.basked.sqliteexample;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//fixme 1 acrion) Create CLASS HELPER
// - cpetify table name, id, fields
public class ContactDdHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="contact_db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = String.format("create table %s (%s number,%s text,%s text);", ContactContract.ContactEntry.TABLE_NAME, ContactContract.ContactEntry.CONTACT_ID, ContactContract.ContactEntry.NAME, ContactContract.ContactEntry.EMAIL);
    public static final String DROP_TABLE = String.format("drop table if exist %s", ContactContract.ContactEntry.TABLE_NAME);
    public ContactDdHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("Database Operations", "Database created...");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL(CREATE_TABLE);
      Log.d("Database Operations", "Table created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      sqLiteDatabase.execSQL(DROP_TABLE);
      onCreate(sqLiteDatabase);
      Log.d("Database Operations", "Table upgrated...");
    }
    public void addContact(int id, String name, String email, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.CONTACT_ID,id);
        contentValues.put(ContactContract.ContactEntry.NAME,name);
        contentValues.put(ContactContract.ContactEntry.EMAIL,email);

        database.insert(ContactContract.ContactEntry.TABLE_NAME,null,contentValues);
        Log.d("Database Operations", "One raw inserted...");

    }
}
