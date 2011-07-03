package net.jumpwire.android.wordsage;

import java.util.ArrayList;

import com.markupartist.android.widget.ActionBar;
import net.jumpwire.android.wordsage.adapters.SearchDefinitionAdapter;
import net.jumpwire.android.wordsage.models.Dictionary;
import net.jumpwire.android.wordsage.utility.UserInterface;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

public class AddWordActivity extends Activity  {
	
	private BaseAdapter mAdapter;
	private Context mContext;
	private ArrayList<String> mDefinitions;
	private String mWord;
	private LinearLayout mLayoutDefinitions;
	private ImageButton mBtnSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_word);
		UserInterface.setActionBar(this, (ActionBar) findViewById(R.id.actionbar));
		
		mContext = this;
		final EditText txtWord = (EditText)findViewById(R.id.txtWord);
		mLayoutDefinitions = (LinearLayout)findViewById(R.id.llDefinitionArea);
		mLayoutDefinitions.setOrientation(LinearLayout.VERTICAL);
		final LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
		final View v = vi.inflate(R.layout.definition_item, null);
        mLayoutDefinitions.addView(v);
        
        final LinearLayout llSearchButton = (LinearLayout)findViewById(R.id.llButtonArea);
         mBtnSearch = (ImageButton)vi.inflate(R.layout.search_button_fragment, null);
        llSearchButton.addView(mBtnSearch);
        
        final EditText txtDefinition = (EditText)mLayoutDefinitions.findViewById(R.id.txtDefinition);
      
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
		
	
		mBtnSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//turn button into refreshing animation
				llSearchButton.removeAllViews();
				mLayoutDefinitions.removeAllViews();
				
			
				mWord = txtWord.getText().toString();
					
				new AddWord().execute();
				
						
				
				
				//llSearchButton.removeAllViews();
				
			}

			});
	}
		
private class AddWord extends AsyncTask<String, Void, Void> {
    	
    	private ProgressBar mProgress;
    	private String mResult = null;  
    	private String mDebugMesage = null;
    	private LinearLayout llSearchButton;
    	
    	@Override
		protected void onPreExecute() {
    		llSearchButton = (LinearLayout)findViewById(R.id.llButtonArea);
    		LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		mProgress = (ProgressBar)vi.inflate(R.layout.progressbar_fragment, null);
			llSearchButton.addView(mProgress);		
    	}

		@Override
		protected Void doInBackground(String... word) {
			mDefinitions = Dictionary.fetchDefinitions(mWord);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void unused) {
			
			if (mDefinitions == null) 
			{
				UserInterface.showToast(mContext, "Definition not found.");
			} else displayDefinitions(mLayoutDefinitions, mWord, mDefinitions);
			
			llSearchButton.removeAllViews();
			llSearchButton.addView(mBtnSearch);
		}
    	
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
