package net.jumpwire.android.eruditer;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.result);
		
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setHomeLogo(R.drawable.logo);
		
		final Action otherAction = new IntentAction(this, new Intent(this, DashboardActivity.class), R.drawable.ic_title_home_default);
        actionBar.addAction(otherAction);
		
		Bundle b = this.getIntent().getExtras();
		 
        int score = b.getInt("SCORE");
        int numberOfQuestions = b.getInt("QUESTION_COUNT");
			
		TextView tvScore = (TextView)this.findViewById(R.id.tvScore);
		tvScore.setText(score + "/" + numberOfQuestions);
	}
}
