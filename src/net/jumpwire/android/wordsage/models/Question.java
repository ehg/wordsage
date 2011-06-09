package net.jumpwire.android.wordsage.models;

import java.util.ArrayList;
import java.util.Collections;

import net.jumpwire.android.wordsage.database.DictionaryOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Question {

	String mWord;
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
		Question question = Dictionary.getRandomWord(context);	
		question.setAnswers(Dictionary.getRandomDefinitions(context, question));
		
		return question;
	}
	public void setAnswers(ArrayList<String> mAnswers) {
		this.mAnswers = mAnswers;
	}
	public ArrayList<String> getAnswers() {
		return mAnswers;
	}

	
	
}
