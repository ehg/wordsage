package net.jumpwire.android.wordsage.adapters;

import java.util.ArrayList;

import net.jumpwire.android.wordsage.R;
import net.jumpwire.android.wordsage.models.Dictionary;
import net.jumpwire.android.wordsage.utility.UserInterface;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchDefinitionAdapter extends BaseAdapter {
	   private ArrayList<String> mDefinitions;
	   private Context mContext;
	   private String mWord;

       public SearchDefinitionAdapter (Context context, ListView listView, ArrayList<String> definitions, String word) {
               mContext = context;
               mDefinitions = definitions;
               mWord = word;
       }	

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
               View v = convertView;
               final String definition = mDefinitions.get(position);
               
               if (v == null) {
                   LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   v = vi.inflate(R.layout.definition_item, null);
               }
               
               if (definition != null) {
            	   EditText txtDefinition = (EditText)v.findViewById(R.id.txtDefinition);
                   ImageButton btnAdd = (ImageButton)v.findViewById(R.id.btnAdd);
                   
                   txtDefinition.setText(definition);
                   
                   btnAdd.setOnClickListener(new View.OnClickListener(	) {
                   @Override
					public void onClick(View v) {
						addWord(definition);		
					}	
                   });
                
               }
               return v;
       }

	@Override
	public int getCount() {
		if (mDefinitions != null)
			return mDefinitions.size();
		else return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mDefinitions != null)
			return mDefinitions.get(position);	
		else return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public static boolean notEmpty(String s) {
		 return (s != null && s.length() > 0);
	}
	
	private void addWord(final String definition) {
		String text = new String();
	      
		if (notEmpty(mWord) && notEmpty(definition))
		{
		   if (Dictionary.notDuplicate(mContext, mWord, definition))
		   {
				Dictionary.addWord(mContext, mWord, definition);
				text = "Word added to dictionary!";
		   }
		   else
		   {
			   text = "Word already in dictionary.";   
		   }
		   UserInterface.showToast(mContext, text);
		}
	}
	
}
