package com.example.sqllite_notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "NoteStorage.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "note_storage";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "note_title";
    private static final String COLUMN_CONTENT = "note_content";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_CONTENT + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNote(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_CONTENT, content);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1)
            Toast.makeText(context, "Failed to Add Note", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Successfully Added Note", Toast.LENGTH_SHORT).show();
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) cursor = db.rawQuery(query, null);

        return cursor;
    }

    public void updateData(String row_id, String title, String content) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // Put your new Values
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_CONTENT, content);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[] {row_id});
        if(result == -1)
            Toast.makeText(context, "Failed to update Note", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Successfully updated Note", Toast.LENGTH_SHORT).show();
    }

    public void deleteRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[] {row_id});
        if(result == -1)
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Successfully deleted note", Toast.LENGTH_SHORT).show();
    }
}
