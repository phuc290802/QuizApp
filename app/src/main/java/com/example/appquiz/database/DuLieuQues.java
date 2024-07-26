package com.example.appquiz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquiz.model.RankModel;

import java.util.ArrayList;
import java.util.List;

public class DuLieuQues {

    private SQLiteDatabase db;
    private DbQuery dhHelper;
    private Context context;
    public static int size_list;
    private int id_db;

    public int getId_db() {
        return id_db;
    }

    public void setId_db(int id_db) {
        this.id_db = id_db;
    }

    public DuLieuQues(Context context) {
        this.context = context;
        dhHelper = new DbQuery(context);
        db=dhHelper.getWritableDatabase();
    }
    public int InseartDiem (RankModel r)
    {
        ContentValues values = new ContentValues();
        values.put("ID", r.getId());
        values.put("name", r.getName());
        values.put("score", String.valueOf(r.getScore()));
        values.put("second", String.valueOf(r.getSecond()));
        values.put("minute", String.valueOf(r.getMinute()));
        values.put("correct", String.valueOf(r.getCorrect()));
        values.put("wrong", String.valueOf(r.getWrong()));
        values.put("unattemp", String.valueOf(r.getUnattemp()));
        values.put("testid", String.valueOf(r.getTestId()));
        long kq=db.insert("TABLE_QUESTION",null,values);
        if(kq==0)
        {
            return -1;
        }
        return 1;
    }
    public List<String> getAllQues()
    {
        List<String> ls= new ArrayList<>();
        Cursor c =db.query("TABLE_QUESTION",null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false)
        {
            RankModel r = new RankModel();
            r.setId(c.getInt(0));
            r.setName(c.getString(1));
            r.setScore(c.getInt(2));
            r.setSecond(c.getInt(3));
            r.setMinute(c.getInt(4));
            r.setCorrect(c.getInt(5));
            r.setWrong(c.getInt(6));
            r.setUnattemp(c.getInt(7));
            r.setTestId(c.getInt(8));
            String chuoi ="ID : "+r.getId()+"---"+"Môn học : "+r.getName()+ "---"+"Score : "+ r.getScore()+"Time : ";
            ls.add(chuoi);
            c.moveToNext();

        }
        c.close();
        return ls;
    }
    public void sizeList()
    {
        size_list=1;
        List<String> ls= new ArrayList<>();
        Cursor c =db.query("TABLE_QUESTION",null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false)
        {
            c.moveToNext();
            setSize_list(getSize_list()+1);
        }
        c.close();

    }
    public void  getAllQuesToList(List<RankModel> list_rank)
    {
        List<String> ls= new ArrayList<>();
        Cursor c =db.query("TABLE_QUESTION",null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false)
        {
            RankModel r = new RankModel();
            r.setId(c.getInt(0));
            r.setName(c.getString(1));
            r.setScore(c.getInt(2));
            r.setSecond(c.getInt(3));
            r.setMinute(c.getInt(4));
            r.setCorrect(c.getInt(5));
            r.setWrong(c.getInt(6));
            r.setUnattemp(c.getInt(7));
            r.setTestId(c.getInt(8));
            c.moveToNext();
            list_rank.add(new RankModel(r.getId(),r.getName(),r.getScore(),r.getSecond(),r.getMinute(),r.getCorrect(),r.getWrong(),r.getUnattemp(),r.getTestId()));
        }
        c.close();

    }

    public static int getSize_list() {
        return size_list;
    }

    public static void setSize_list(int size_list) {
        DuLieuQues.size_list = size_list;
    }
}
