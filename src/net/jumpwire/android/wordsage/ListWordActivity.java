package net.jumpwire.android.wordsage;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

import net.jumpwire.android.wordsage.adapters.WordListAdapter;
import net.jumpwire.android.wordsage.database.DictionaryOpenHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListWordActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.word_list);
		
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setHomeLogo(R.drawable.logo);
		
		final Action otherAction = new IntentAction(this, new Intent(this, DashboardActivity.class), R.drawable.ic_title_home_default);
        actionBar.addAction(otherAction);
		
		ListView lv = (ListView)findViewById(R.id.lvWords);
		DictionaryOpenHelper doh = new DictionaryOpenHelper(getApplicationContext());
		SQLiteDatabase db = doh.getWritableDatabase();


        String[] columns = new String[] {"_id" , "word", "definition"};

        
        Cursor cursor = db.query(true, "dictionary", columns, null , null, null, null, null, null);
        startManagingCursor(cursor);
        Log.d("TAG", cursor.getCount() + "");
		
		WordListAdapter adapter = new WordListAdapter (getApplicationContext(), cursor);
		
		lv.setAdapter(adapter);
		
	}
}
