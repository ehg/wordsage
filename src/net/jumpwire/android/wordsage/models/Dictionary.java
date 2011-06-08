package net.jumpwire.android.wordsage.models;
import java.util.ArrayList;
import java.util.List;

import net.jumpwire.android.wordsage.database.DictionaryOpenHelper;
import net.jumpwire.android.wordsage.dictionary_api.DictionaryDotCom;
import net.jumpwire.android.wordsage.dictionary_api.OnlineDictionary;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


@Root(name=("dictionary"))
public class Dictionary {
	
	@Attribute(name="totalresults")
	public int totalResults;
	
	@ElementList(name="entry",inline=true)
	public List<Entry> entries;
	
	public static boolean notDuplicate(Context context, String m_word, String definition) {
		DictionaryOpenHelper dbOpen = new DictionaryOpenHelper(context);
		SQLiteDatabase db = dbOpen.getReadableDatabase();
		boolean notDuplicate = true;
		
		Cursor c = db.query(true, "dictionary", new String[] { "word", "definition" }, 
							"UPPER(word) = UPPER(?) AND UPPER(definition) = UPPER(?)", 
							new String[] {m_word, definition}, null, null, null, null);
		
		if (c.getCount() > 0) notDuplicate = false;
		
		c.close();
		db.close();
		
		return notDuplicate;
	}
	
	public static void addWord(Context context, String word, String definition)
	{
		DictionaryOpenHelper dbOpen = new DictionaryOpenHelper(context);
		SQLiteDatabase db = dbOpen.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
       
       cv.put("word", word);
       cv.put("definition", definition);
       db.insert(DictionaryOpenHelper.DICTIONARY_TABLE_NAME, null, cv);
       db.close();
	}
	
	public static ArrayList<String> fetchDefinitions(String word) {
		//get definition from dictionary.com
		OnlineDictionary dict = new DictionaryDotCom();
		return dict.getWordDefinitions(word);
	}
}
