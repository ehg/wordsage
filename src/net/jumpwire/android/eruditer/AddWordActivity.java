package net.jumpwire.android.eruditer;

import java.util.ArrayList;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

import net.jumpwire.android.eruditer.database.DictionaryOpenHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AddWordActivity extends Activity {
	
	private DefinitionAdapter m_adapter;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_word);
		
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setHomeLogo(R.drawable.logo);
		
		final Action otherAction = new IntentAction(this, new Intent(this, DashboardActivity.class), R.drawable.ic_title_home_default);
        actionBar.addAction(otherAction);
		
		final EditText txtWord = (EditText)findViewById(R.id.txtWord);
		
		
		Button btnSearch = (Button)findViewById(R.id.btnWordSearch);
		
		
		final ListView lv = (ListView)findViewById(R.id.lstDefinitions);
		
			
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//turn button into refreshing animation
				String word = txtWord.getText().toString();
				ArrayList<String> definitions = fetchDefinitions(word);
					
				if (definitions == null) 
				{
					Toast toast = Toast.makeText(getApplicationContext(), "Definition not found.", 5);
					toast.show();
				}
				else
				{
					m_adapter = new DefinitionAdapter(getApplicationContext(), lv, definitions, word);
					lv.setAdapter(m_adapter);
					
			
				}}});
	}
		
	
	protected ArrayList<String> fetchDefinitions(String word) {
		//get definition from dictionary.com
		OnlineDictionary dict = new DictionaryDotCom();
		return dict.getWordDefinitions(word);
	}

	
	

}
