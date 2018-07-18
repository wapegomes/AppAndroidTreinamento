package br.com.wpgomes.treinamento.appcasttreinamento.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.wpgomes.treinamento.appcasttreinamento.util.Constants;


public class SQLiteHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "marvelapp.db";
    private static final int DATABASE_VERSION = 4;


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Constants.DDL_CHARACTER);
        sqLiteDatabase.execSQL(Constants.DDL_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.CHARACTER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.EVENT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public static SQLiteDatabase getDatabase(Context context) {
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        return dbHelper.getWritableDatabase();
    }
}
