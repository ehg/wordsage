package net.jumpwire.android.eruditer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "eruditerWordDB";
    
    public static final String DICTIONARY_TABLE_NAME = "dictionary";
    
    private static final String KEY_WORD = "word";
    private static final String KEY_DEFINITION = "definition";
    
    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                KEY_WORD + " TEXT, " +
                KEY_DEFINITION + " TEXT);";

    public DictionaryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
        ContentValues cv = new ContentValues();
        
        cv.put("word", "Erudite");
        cv.put("definition", "Having or showing great knowledge or learning.");
        db.insert(DICTIONARY_TABLE_NAME, null, cv);
        cv.clear();
        
        cv.put("word", "Idiot");
        cv.put("definition", "A mentally handicapped person.");
        db.insert(DICTIONARY_TABLE_NAME, null, cv);
        cv.clear();
        
        cv.put("word", "Duckling");
        cv.put("definition", "A young duck.");
        db.insert(DICTIONARY_TABLE_NAME, null, cv);
        cv.clear();
        
        cv.put("word", "Test");
        cv.put("definition", "Take measures to check the quality, performance, or reliability of (something), esp. before putting it into widespread use or practice.");
        db.insert(DICTIONARY_TABLE_NAME, null, cv);
        cv.clear();
        	
        cv.put("word", "Lovely");
        cv.put("definition", "A glamorous woman or girl.");
        db.insert(DICTIONARY_TABLE_NAME, null, cv);
        db.close();
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	

}