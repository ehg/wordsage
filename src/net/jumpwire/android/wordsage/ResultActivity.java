package net.jumpwire.android.wordsage;

import net.jumpwire.android.wordsage.adapters.IncorrectWordAdapter;
import net.jumpwire.android.wordsage.utility.UserInterface;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.result);	
		UserInterface.setActionBar(this,
				(ActionBar) findViewById(R.id.actionbar));
	
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Bundle b = this.getIntent().getExtras();
		 
        int score = b.getInt("SCORE");
        int numberOfQuestions = b.getInt("QUESTION_COUNT");
        
		TextView tvScore = (TextView)this.findViewById(R.id.tvScore);
		tvScore.setText(score + " / " + numberOfQuestions);
		
		TextView tvWrong = (TextView)this.findViewById(R.id.tvWrong);
		if (TestActivity.mIncorrectQuestions.size() == 0) 
			tvWrong.setVisibility(View.INVISIBLE);
		else tvWrong.setVisibility(View.VISIBLE);
		
		ListView incorrect = (ListView)findViewById(R.id.lvWrongWords);
		
		IncorrectWordAdapter adapter = new IncorrectWordAdapter(this, incorrect, TestActivity.mIncorrectQuestions);
		incorrect.setAdapter(adapter);
	}
	
	
}
