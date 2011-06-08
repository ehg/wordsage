package net.jumpwire.android.wordsage.adapters;

import net.jumpwire.android.wordsage.R;
import net.jumpwire.android.wordsage.R.id;
import net.jumpwire.android.wordsage.R.layout;
import net.jumpwire.android.wordsage.database.DictionaryOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class WordListAdapter extends CursorAdapter {

	private final LayoutInflater mInflater;
	private Cursor cursor;

    public WordListAdapter (Context context, Cursor c) {
        super(context, c);
        mInflater = LayoutInflater.from(context);   
        cursor = c;
    }

    @Override
    public View newView(Context context, Cursor c, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.word_item, parent, false);
        bindView(v, context, c);
        return v;
    }

    @Override
    public void bindView(View v, Context context, Cursor c) {
    	int wordCol = c.getColumnIndexOrThrow("word");
        int defCol = c.getColumnIndexOrThrow("definition");
        int idCol = c.getColumnIndexOrThrow("_id");

        String word = c.getString(wordCol);
        String def = c.getString(defCol);
        int id = c.getInt(idCol);
        
        TextView word_text = (TextView) v.findViewById(R.id.lblListWord);
        TextView definition_text = (TextView) v.findViewById(R.id.lblListDefinition);
        ImageButton btnDelete = (ImageButton)v.findViewById(R.id.btnDelete);

        word_text.setText(word);
        Log.d("TAG", id + "");
        definition_text.setText(def);
        
        if (btnDelete != null)
        {
	        btnDelete.setId(id);
	        btnDelete.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int id = v.getId();
					Log.d("TAGGGGG", id + "");
					DictionaryOpenHelper dho = new DictionaryOpenHelper(v.getContext());
					SQLiteDatabase db = dho.getWritableDatabase();
					db.delete("dictionary", "_id = ?", new String [] { id + "" });
					db.close();
					cursor.requery();
				}
	
		
			});
        }
        
    }
    
    

}
