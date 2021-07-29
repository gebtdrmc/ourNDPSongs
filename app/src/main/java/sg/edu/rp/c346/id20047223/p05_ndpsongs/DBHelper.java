package sg.edu.rp.c346.id20047223.p05_ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {

        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN  module_name TEXT ");
    }


    private static final String DATABASE_NAME = "song.db";
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG_TITLE = "song_title";
    private static final String COLUMN_SONG_SINGER = "singer";
    private static final String COLUMN_SONG_YEAR = "_year";
    private static final String COLUMN_SONG_RATING = "_rating";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SONG_TITLE + " TEXT ," + COLUMN_SONG_SINGER
                + "TEXT , " + COLUMN_SONG_YEAR + " INTEGER ," + COLUMN_SONG_RATING + "INTEGER ) ";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        //Dummy records, to be inserted when the database is created
        for (int i = 0; i< 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_SONG_TITLE, "Data number " + i);
            db.insert(TABLE_SONG, null, values);
            values.put(COLUMN_SONG_SINGER, "Data number " + i);
            db.insert(TABLE_SONG, null, values);
            values.put(String.valueOf(COLUMN_SONG_YEAR), "Data number " + i);
            db.insert(TABLE_SONG, null, values);
            values.put(String.valueOf(COLUMN_SONG_RATING), "Data number " + i);
            db.insert(TABLE_SONG, null, values);
        }
        Log.i("info", "dummy records inserted");

    }

    public long insertSongTitle(String songTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, songTitle);
        long result = db.insert(TABLE_SONG, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }

    public long insertSongSingers(String songSinger) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_SINGER, songSinger);
        long result = db.insert(TABLE_SONG, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }
    public long insertSongYear(String songYear) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, songYear);
        long result = db.insert(TABLE_SONG, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }

    public long insertSongRating(String songRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(String.valueOf(COLUMN_SONG_RATING), songRating);
        long result = db.insert(TABLE_SONG, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_SONG_TITLE + COLUMN_SONG_SINGER + COLUMN_SONG_YEAR + COLUMN_SONG_RATING
                + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String songTitle = cursor.getString(1);
                String songSinger = cursor.getString(2);
                int songYear = cursor.getInt(3);
                int songRating = cursor.getInt(4);
                Song song = new Song(id, songTitle, songSinger, songYear, songRating);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
    public int updateNote(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, data.getSongTitle());
        values.put(COLUMN_SONG_SINGER, data.getSongTitle());
        values.put(String.valueOf(COLUMN_SONG_YEAR), data.getSongTitle());
        values.put(String.valueOf(COLUMN_SONG_RATING), data.getSongTitle());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;
    }
    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;
    }

    public ArrayList<Song> getAllSong(String keyword) {
        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGER, COLUMN_SONG_YEAR, COLUMN_SONG_RATING};
        String condition = COLUMN_SONG_TITLE + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String songTitle = cursor.getString(1);
                String songSinger = cursor.getString(2);
                int songYear = cursor.getInt(3);
                int songRating = cursor.getInt(4);
                Song song = new Song(id, songTitle, songSinger, songYear, songRating);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getSongByStars(){
        ArrayList<Song> songlist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] coloumns = {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGER, COLUMN_SONG_YEAR, COLUMN_SONG_RATING};
        String condition = COLUMN_SONG_RATING + " = 5";

        Cursor cursor;
        cursor = db.query(TABLE_SONG, coloumns, condition, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String songSinger = cursor.getString(2);
                int songYear = cursor.getInt(3);
                int songRating = cursor.getInt(4);

                Song newSong = new Song(id, title, songSinger, songYear, songRating);
                songlist.add(newSong);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songlist;
    }
}
