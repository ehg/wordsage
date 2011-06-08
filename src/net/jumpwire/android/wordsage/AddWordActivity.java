package net.jumpwire.android.wordsage;

import java.util.ArrayList;

import com.markupartist.android.widget.ActionBar;
import net.jumpwire.android.wordsage.adapters.SearchDefinitionAdapter;
import net.jumpwire.android.wordsage.models.Dictionary;
import net.jumpwire.android.wordsage.utility.UserInterface;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class AddWordActivity extends Activity {
	
	private BaseAdapter mAdapter;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_word);
		UserInterface.setActionBar(this, (ActionBar) findViewById(R.id.actionbar));
		
		mContext = this;
		final EditText txtWord = (EditText)findViewById(R.id.txtWord);
		final LinearLayout llDefinitionArea = (LinearLayout)findViewById(R.id.llDefinitionArea);
		llDefinitionArea.setOrientation(LinearLayout.VERTICAL);
		LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
		final View v = vi.inflate(R.layout.definition_item, null);
        llDefinitionArea.addView(v);
        
        final EditText txtDefinition = (EditText)llDefinitionArea.findViewById(R.id.txtDefinition);
      
        ImageButton btnAdd = (ImageButton)v.findViewById(R.id.btnAdd);
        
        btnAdd.setOnClickListener(new View.OnClickListener(	) {
			
			@Override
			public void onClick(View v) {
				  String definition = txtDefinition.getText().toString();
			      String word = txtWord.getText().toString();
			      String text = new String();
			      
				   if (notEmpty(word) && notEmpty(definition))
					{
					   if (Dictionary.notDuplicate(mContext, word, definition))
					   {
							Dictionary.addWord(mContext, word, definition);
							text = "Word added to dictionary!";
					   }
					   else
					   {
						   text = "Word already in dictionary.";   
					   }
					   UserInterface.showToast(mContext, text);
					}
			}
		});
		
		Button btnSearch = (Button)findViewById(R.id.btnWordSearch);	
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//turn button into refreshing animation
				llDefinitionArea.removeViewAt(0);
				String word = txtWord.getText().toString();
				ArrayList<String> definitions = Dictionary.fetchDefinitions(word);
					
				if (definitions == null) 
				{
					UserInterface.showToast(mContext, "Definition not found.");
				} else displayDefinitions(llDefinitionArea, word, definitions);
				
			}

			});
	}
		
	private void displayDefinitions(
			final LinearLayout llDefinitionArea, String word,
			ArrayList<String> definitions) {
		ListView lv = new ListView(mContext);
		llDefinitionArea.addView(lv);
		mAdapter = new SearchDefinitionAdapter(mContext, lv, definitions, word);
		lv.setAdapter(mAdapter);
	}
	
	public static boolean notEmpty(String s) {
		 return (s != null && s.length() > 0);
		}
	

	

}
