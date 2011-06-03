package net.jumpwire.android.eruditer;

import net.jumpwire.android.eruditer.database.DictionaryOpenHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddWordActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_word);
		
		final EditText txtWord = (EditText)findViewById(R.id.txtWord);
		final EditText txtDefinition = (EditText)findViewById(R.id.txtDefinition);
		
		Button btnSearch = (Button)findViewById(R.id.btnWordSearch);
		Button btnAdd = (Button)findViewById(R.id.btnAdd);
		
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String word = txtWord.getText().toString();
				String definition = txtDefinition.getText().toString();
			
				if (notEmpty(word) && notEmpty(definition))
				{
					addWord(word, definition);
					Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
			    	startActivityForResult(myIntent, 0);
				}
				
			}
		});
		
	}
	
	public void addWord(String word, String definition)
	{
		DictionaryOpenHelper dbOpen = new DictionaryOpenHelper(getApplicationContext());
		SQLiteDatabase db = dbOpen.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
        
        cv.put("word", word);
        cv.put("definition", definition);
        db.insert(DictionaryOpenHelper.DICTIONARY_TABLE_NAME, null, cv);
        db.close();
	}
	
	public static boolean notEmpty(String s) {
		 return (s != null && s.length() > 0);
		}
}
