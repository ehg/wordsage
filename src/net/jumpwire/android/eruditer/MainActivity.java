package net.jumpwire.android.eruditer;

import java.util.ArrayList;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

import net.jumpwire.android.eruditer.models.Question;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private int mAnsweredCount = 0;
	private int mCorrectlyAnsweredCount = 0;
	private ArrayList<String> mAnsweredWords = new ArrayList<String>();
	
	private Question mQuestion;
	
	@Override
	public void onStart() {
		super.onStart();
		populateQuestion();
        
        //listen for click, if answer selected, move on to next question
        
        Button btnAnswer = (Button)findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RadioGroup rg = (RadioGroup)findViewById(R.id.rgAnswers);
				RadioButton rdSelectedAnswer = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
				String ansText = rdSelectedAnswer.getText().toString();
				Log.d("ERD", ansText);
				Log.d("ERD", mQuestion.getDefinition());
				
				if (ansText.equals(mQuestion.getDefinition()))
				{
					Toast toast = Toast.makeText(getApplicationContext(), "Correct! You beast!", 5);
					toast.show();
					
					//increment correct answer count
					mCorrectlyAnsweredCount++;
				}
				
				//increment question count
				mAnsweredCount++;
				//add word to answered list
				mAnsweredWords.add(mQuestion.getName());
				
				if (mAnsweredCount == 5)
				{
					//reset counters
					
					//show result activity
					Intent myIntent = new Intent(getApplicationContext(), ResultActivity.class);
					
					Bundle b = new Bundle();
					 
                    b.putInt("SCORE", mCorrectlyAnsweredCount);
                    b.putInt("QUESTION_COUNT", mAnsweredCount);

                    myIntent.putExtras(b);
                    
                    
                    mAnsweredCount = 0;
                    mAnsweredWords.clear();
                    mCorrectlyAnsweredCount = 0;
                    
	                startActivityForResult(myIntent, 0);
				}
				else
				{
					//move to next question
					populateQuestion();
				}	
			}	
		});     
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setHomeLogo(R.drawable.logo);
		
		final Action otherAction = new IntentAction(this, new Intent(this, DashboardActivity.class), R.drawable.ic_title_home_default);
        actionBar.addAction(otherAction);
        
    }
    
	private void populateQuestion() {
		//get question
		mQuestion = Question.getRandom(this);
        
		while (mAnsweredWords.contains(mQuestion.getName()))
		{
			mQuestion = Question.getRandom(this);
		}
		
        //set label
        TextView lblWord = (TextView) findViewById(R.id.lblWord);
        lblWord.setText(mQuestion.getName());
        
        //set answers to radios
        RadioButton radio = null;
        
        ArrayList<String> answers = mQuestion.getAnswers();
        
        for (int i = 0; i < answers.size(); i++) {
			radio = (RadioButton) findViewById(getResources().getIdentifier("radio" + i, "id", getPackageName()));
			radio.setText(answers.get(i));
		}	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.itmAddWords:
	    	Intent myIntent = new Intent(getApplicationContext(), AddWordActivity.class);
	    	startActivityForResult(myIntent, 0);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
    
}