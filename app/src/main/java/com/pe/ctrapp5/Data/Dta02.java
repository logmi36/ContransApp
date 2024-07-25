package com.pe.ctrapp5.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import com.pe.ctrapp5.Model.Obj02;

public class Dta02 extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "obj02.sqlite";
    private static final String TABLE_NAME = "obj02";

    public Dta02(Context _context)
    {
        super(_context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE "+ TABLE_NAME + " (f01 INTEGER PRIMARY KEY AUTOINCREMENT, f02 INTEGER,f03 TEXT,f04 TEXT,f05 TEXT,f06 TEXT,f07 TEXT,f08 TEXT,f09 TEXT,f10 TEXT,f11 TEXT,f12 TEXT,f13 TEXT,f14 TEXT,f15 TEXT,f16 TEXT,f17 TEXT,f18 TEXT,f19 TEXT,f20 TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insert(Obj02 item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("f02", item.getF02());
        cv.put("f03", item.getF03());
        cv.put("f04", item.getF04());
        cv.put("f05", item.getF05());
        cv.put("f06", item.getF06());
        cv.put("f07", item.getF07());
        cv.put("f08", item.getF08());
        cv.put("f09", item.getF09());
        cv.put("f10", item.getF10());
        cv.put("f11", item.getF11());
        cv.put("f12", item.getF12());
        cv.put("f13", item.getF13());
        cv.put("f14", item.getF14());
        cv.put("f15", item.getF15());
        cv.put("f16", item.getF16());
        cv.put("f17", item.getF17());
        cv.put("f18", item.getF18());
        cv.put("f19", item.getF19());
        cv.put("f20", item.getF20());
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }

    public List<Obj02> List()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT f01, f02, f03, f04, f05, f06 ,f07,f08,f09,f10,f11,f12, f13, f14, f15, f16, f17, f18, f19, f20 from " +TABLE_NAME + " order by f01 desc";
        Cursor cursor = db.rawQuery(query, null);
        List<Obj02> items = new ArrayList<>();
        Obj02 item;
        item=new Obj02();
        Integer n=0;
        while (cursor.moveToNext())
        {
            n=n+1;
            item = new Obj02();
            item.setF01(cursor.getString(0));
            item.setF02(cursor.getString(1));
            item.setF03(cursor.getString(2));
            item.setF04(cursor.getString(3));
            item.setF05(cursor.getString(4));
            item.setF06(cursor.getString(5));
            item.setF07(cursor.getString(6));
            item.setF08(cursor.getString(7));
            item.setF09(cursor.getString(8));
            item.setF10(cursor.getString(9));
            item.setF11(cursor.getString(10));
            item.setF12(cursor.getString(11));
            item.setF13(cursor.getString(12));
            item.setF14(cursor.getString(13));
            item.setF15(cursor.getString(14));
            item.setF16(cursor.getString(15));
            item.setF17(cursor.getString(16));
            item.setF18(cursor.getString(17));
            item.setF19(cursor.getString(18));
            item.setF20(cursor.getString(19));
            items.add(item);
        }
        cursor.close();

        return  items;
    }


    public void deleteById(String f01)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"f01 = ? ",new String[]{f01} );
        db.close();
    }



    public int update(Obj02 obj02)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvlas = new ContentValues();
        cvlas.put("f02", obj02.getF02());
        cvlas.put("f03", obj02.getF03());
        cvlas.put("f04", obj02.getF04());
        cvlas.put("f05", obj02.getF05());
        cvlas.put("f06", obj02.getF06());
        cvlas.put("f07", obj02.getF07());
        cvlas.put("f08", obj02.getF08());
        cvlas.put("f09", obj02.getF09());
        cvlas.put("f10", obj02.getF10());
        cvlas.put("f11", obj02.getF11());
        cvlas.put("f12", obj02.getF12());
        cvlas.put("f13", obj02.getF13());
        cvlas.put("f14", obj02.getF14());
        cvlas.put("f15", obj02.getF15());
        cvlas.put("f16", obj02.getF16());
        cvlas.put("f17", obj02.getF17());
        cvlas.put("f18", obj02.getF18());
        cvlas.put("f19", obj02.getF19());
        cvlas.put("f20", obj02.getF20());

        int ans = db.update(TABLE_NAME, cvlas, "f01=?", new String[]{String.valueOf(obj02.getF01())});
        return ans;
    }



}
