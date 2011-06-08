package net.jumpwire.android.wordsage.models;

import java.util.ArrayList;
import java.util.Collections;

import net.jumpwire.android.wordsage.database.DictionaryOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Question {

	private String mWord;
	private String mDefinition;
	private ArrayList<String> mAnswers;
		
	public void setName(String mName) {
		this.mWord = mName;
	}
	public String getName() {
		return mWord;
	}
	
	public void setDefinition(String mDefinition) {
		this.mDefinition = mDefinition;
	}
	public String getDefinition() {
		return mDefinition;
	}

	public static Question getRandom(Context context)
	{
		DictionaryOpenHelper dbOpen = new DictionaryOpenHelper(context);
		SQLiteDatabase db = dbOpen.getWritableDatabase();
		Question question = new Question();
		
		// get a random word and its definition
		Cursor c = db.query(true, "dictionary", new String[] { "word", "definition" }, null, null, null, null, "RANDOM()", "1");
		c.moveToFirst();
		question.setName(c.getString(c.getColumnIndex("word")));
		question.setDefinition(c.getString(c.getColumnIndex("definition")));
		c.close();
		
		// populate an array with 4 random definitions and the correct one. don't select current word's definition
		c = db.query(true, "dictionary", new String[] { "definition" }, "word != ?" , new String [] { question.mWord }, 
					  null, null, "RANDOM()", "4");
		c.moveToFirst();
		
		ArrayList<String> answers = new ArrayList<String>();
		
		answers.add(question.getDefinition());
			
		while (c.isAfterLast() == false)
		{
			answers.add(c.getString(c.getColumnIndex("definition")));
			c.moveToNext();
		}
		c.close();
		db.close();
		Collections.shuffle(answers);
		
		question.setAnswers(answers);
		
		return question;
	}
	public void setAnswers(ArrayList<String> mAnswers) {
		this.mAnswers = mAnswers;
	}
	public ArrayList<String> getAnswers() {
		return mAnswers;
	}

	
	
}
