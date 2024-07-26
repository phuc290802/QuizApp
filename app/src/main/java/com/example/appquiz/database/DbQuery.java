package com.example.appquiz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.GridView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbQuery extends SQLiteOpenHelper {

    private static final String TABLE_QUESTION="QuestionBank";
    public static final String id = "Id";
    public static final String second = "second";
    public static final String minute = "minute";
    public static final String name = "name";
    public static final String score = "score";
    public static final String correct = "correct";
    public static final String wrong = "wrong";
    public static final String unattemp = "unattemp";
    public static final String testid = "testid";

    public DbQuery(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQl_DIEM);
    }
    public static final String SQl_DIEM ="CREATE TABLE TABLE_QUESTION "+"("
            + id + " Integer Primary key, "
            + name + " Text,"
            + score + " Text,"
            + second + " Text,"
            + minute + " Text, "
            + correct + " text, "
            + wrong + " text, "
            + unattemp + " text, "
            + "testid" + " text)";

    //chạy câu truy vấn SQL để tạo bảng;
    public DbQuery(Context context)
    {
        super(context,"QLD.db",null,1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_QUESTION);
        //tạo lại
        onCreate(db);
    }


}
