package net.jumpwire.android.eruditer;

import android.app.Activity;
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
		
		Bundle b = this.getIntent().getExtras();
		 
        int score = b.getInt("SCORE");
        int numberOfQuestions = b.getInt("QUESTION_COUNT");
			
		TextView tvScore = (TextView)this.findViewById(R.id.tvScore);
		tvScore.setText(score + "/" + numberOfQuestions);
	}
}
