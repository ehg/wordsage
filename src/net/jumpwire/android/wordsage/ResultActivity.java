package net.jumpwire.android.wordsage;

import net.jumpwire.android.wordsage.utility.UserInterface;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.result);	
		UserInterface.setActionBar(this,
				(ActionBar) findViewById(R.id.actionbar));
		
		Bundle b = this.getIntent().getExtras();
		 
        int score = b.getInt("SCORE");
        int numberOfQuestions = b.getInt("QUESTION_COUNT");
			
		TextView tvScore = (TextView)this.findViewById(R.id.tvScore);
		tvScore.setText(score + " / " + numberOfQuestions);
	}
}
