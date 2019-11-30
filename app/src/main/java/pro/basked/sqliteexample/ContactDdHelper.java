package pro.basked.sqliteexample;


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

    public static final String CREATE_TABLE = "create table"+ContactContract.ContactEntry.TABLE_NAME+" ("+
            ContactContract.ContactEntry.CONTACT_ID +" number,"+
            ContactContract.ContactEntry.NAME +" text,"+
            ContactContract.ContactEntry.EMAIL +" text);";
    public static final String DROP_TABLE = "drop table if exist "+ContactContract.ContactEntry.TABLE_NAME;
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
}
