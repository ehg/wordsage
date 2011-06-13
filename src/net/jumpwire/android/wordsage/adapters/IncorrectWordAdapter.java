package net.jumpwire.android.wordsage.adapters;

import java.util.ArrayList;

import net.jumpwire.android.wordsage.R;
import net.jumpwire.android.wordsage.models.Question;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class IncorrectWordAdapter extends BaseAdapter {
	   private ArrayList<Question> mQuestions;
	   private Context mContext;
	

       public IncorrectWordAdapter (Context context, ListView listView, ArrayList<Question> questions) {
               mContext = context;
               mQuestions = questions;
       }	

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
               View v = convertView;
 
               if (v == null) {
                   LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   v = vi.inflate(R.layout.result_word_item, null);
               }     
               
               Question q = mQuestions.get(position);
               TextView word = (TextView)v.findViewById(R.id.lblWord);
               TextView def = (TextView)v.findViewById(R.id.lblDefinition);
               Log.d("TAGuuiuiui", q.getName());
               word.setText(q.getName());
               def.setText(q.getDefinition());
               return v;
       }

	@Override
	public int getCount() {
		if (mQuestions != null)
			return mQuestions.size();
		else return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mQuestions != null)
			return mQuestions.get(position);	
		else return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
}
