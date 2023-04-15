package com.itptit.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.itptit.myapplication.model.MyModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_thuc_hanh";

    // Country table name
    private static final String TABLE_THUC_HANH = "tbl_thuc_hanh";

    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String JOB_NAME = "job_name";
    private static final String JOB_DETAIL = "job_detail";
    private static final String FINISH_DATE = "finish_date";
    private static final String JOB_STATUS = "job_status";
    private static final String COLLABORATION = "collaboration";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_THUC_HANH + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + JOB_NAME + " TEXT,"
                + JOB_DETAIL + " TEXT,"
                + FINISH_DATE + " TEXT,"
                + JOB_STATUS + " TEXT,"
                + COLLABORATION + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THUC_HANH);
        // Create tables again
        onCreate(db);
    }

    // Adding new country
    public void addData(MyModel myModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JOB_NAME, myModel.getJobName());
        values.put(JOB_DETAIL, myModel.getJobDetail());
        values.put(FINISH_DATE, myModel.getFinishDate());
        values.put(JOB_STATUS, myModel.getJobStatus());
        values.put(COLLABORATION, myModel.getCollaboration());

        // Inserting Row
        db.insert(TABLE_THUC_HANH, null, values);
        db.close(); // Closing database connection
    }

    // Getting single row
    MyModel getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_THUC_HANH, new String[]{KEY_ID,
                        JOB_NAME,
                        JOB_DETAIL,
                        FINISH_DATE,
                        JOB_STATUS,
                        COLLABORATION
                }, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        // return country
        return new MyModel(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                Integer.parseInt(cursor.getString(5))
        );
    }

    // Get all data
    public List<MyModel> getAllData() {
        List<MyModel> data = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_THUC_HANH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyModel myModel = new MyModel();
                myModel.setId(Integer.parseInt(cursor.getString(0)));
                myModel.setJobName(cursor.getString(1));
                myModel.setJobDetail(cursor.getString(2));
                myModel.setFinishDate(cursor.getString(3));
                myModel.setJobStatus(cursor.getString(4));
                myModel.setCollaboration(Integer.parseInt(cursor.getString(5)));
                // Adding data to list
                data.add(myModel);
            } while (cursor.moveToNext());
        }

        // return data list
        return data;
    }

    // Updating single row
    public int updateData(MyModel myModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, myModel.getId());
        values.put(JOB_NAME, myModel.getJobName());
        values.put(JOB_DETAIL, myModel.getJobDetail());
        values.put(FINISH_DATE, myModel.getFinishDate());
        values.put(JOB_STATUS, myModel.getJobStatus());
        values.put(COLLABORATION, myModel.getCollaboration());

        // updating row
        return db.update(TABLE_THUC_HANH, values, KEY_ID + " = ?",
                new String[]{String.valueOf(myModel.getId())});
    }

    // Deleting single row
    public void deleteData(MyModel myModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
            TABLE_THUC_HANH, KEY_ID + " = ?",
            new String[]{String.valueOf(myModel.getId())});
        db.close();
    }

    // Getting row count
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_THUC_HANH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}