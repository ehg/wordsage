package net.jumpwire.android.eruditer;

import java.util.ArrayList;

import net.jumpwire.android.eruditer.database.DictionaryOpenHelper;



import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class DefinitionAdapter extends BaseAdapter {
	   private ArrayList<String> m_definitions;
	   private Context m_context;
	   private String m_word;

       public DefinitionAdapter (Context context, ListView listView, ArrayList<String> definitions, String word) {
               m_context = context;
               m_definitions = definitions;
               m_word = word;
       }	

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
               View v = convertView;
               if (v == null) {
                   LayoutInflater vi = (LayoutInflater)m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   v = vi.inflate(R.layout.definition_item, null);
               }
               final String definition = m_definitions.get(position);
               if (definition != null) {
            	   EditText txtDefinition = (EditText)v.findViewById(R.id.txtDefinition);
                   txtDefinition.setText(definition);
                   	
                   ImageButton btnAdd = (ImageButton)v.findViewById(R.id.btnAdd);
                   
                   btnAdd.setOnClickListener(new View.OnClickListener(	) {
					
					@Override
					public void onClick(View v) {
						   if (notEmpty(m_word) && notEmpty(definition))
							{
								addWord(m_word, definition);
								Toast toast = Toast.makeText(m_context, "Word added to dictionary!", 5);
								toast.show();
							}
						
					}
				});
                
         
               }
               return v;
       }

	@Override
	public int getCount() {
		if (m_definitions != null)
			return m_definitions.size();
		else return 0;
	}

	@Override
	public Object getItem(int position) {
		if (m_definitions != null)
			return m_definitions.get(position);	
		else return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public static boolean notEmpty(String s) {
		 return (s != null && s.length() > 0);
		}
	
	public void addWord(String word, String definition)
	{
		DictionaryOpenHelper dbOpen = new DictionaryOpenHelper(m_context);
		SQLiteDatabase db = dbOpen.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
        
        cv.put("word", word);
        cv.put("definition", definition);
        db.insert(DictionaryOpenHelper.DICTIONARY_TABLE_NAME, null, cv);
        db.close();
	}
}
