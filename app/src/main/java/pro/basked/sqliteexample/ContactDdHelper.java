package pro.basked.sqliteexample;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


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

    public void addContact(int id, String name, String email, SQLiteDatabase database) throws IOException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.CONTACT_ID,id);
        contentValues.put(ContactContract.ContactEntry.NAME,name);
        contentValues.put(ContactContract.ContactEntry.EMAIL,email);

        database.insert(ContactContract.ContactEntry.TABLE_NAME,null,contentValues);
        Log.d("Database Operations", "One raw inserted...");


    }

    public  boolean copyDatabaseFromAssets(Context context, String databaseName , boolean overwrite)  {
        /**
         * Copy database file from assets folder inside the apk to the system database path.
         * @param context Context
         * @param databaseName Database file name inside assets folder
         * @param overwrite True to rewrite on the database if exists
         * @return True if the database have copied successfully or if the database already exists without overwrite, false otherwise.
         */
        File outputFile = context.getDatabasePath(databaseName);
        if (outputFile.exists() && !overwrite) {
            return true;
        }

        outputFile = context.getDatabasePath(databaseName + ".tempbas");
        outputFile.getParentFile().mkdirs();

        try {
            InputStream inputStream = context.getAssets().open(databaseName);
            OutputStream outputStream = new FileOutputStream(outputFile);


            // transfer bytes from the input stream into the output stream
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Close the streams
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            outputFile.renameTo(context.getDatabasePath(databaseName));

        } catch (IOException e) {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            return false;
        }


        return true;
    }



}
