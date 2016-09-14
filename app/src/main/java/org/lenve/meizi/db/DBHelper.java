package org.lenve.meizi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 王松 on 2016/9/13.
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static String DBNAME = "sang.db";
    public final static String CLASSFYTABLE = "classfytable";
    private final static int CURRENTVERSION = 1;
    private final static int STARTVERSION = 1;

    public DBHelper(Context context) {
        super(context, DBNAME, null, CURRENTVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + CLASSFYTABLE + "(_id INTEGER PRIMARY KEY,ID,NAME," +
                "TITLE,KEYWORDS,DESCRIPTION,SEQ);");
        onUpgrade(db, STARTVERSION, CURRENTVERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
