package net.jumpwire.android.wordsage;

import java.util.ArrayList;

import com.markupartist.android.widget.ActionBar;

import net.jumpwire.android.wordsage.models.Question;
import net.jumpwire.android.wordsage.utility.UserInterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity {
	private static final int NUMBER_OF_QUESTIONS = 5;
	
	private int mAnsweredCount = 0;
	private int mCorrectlyAnsweredCount = 0;
	private ArrayList<String> mAnsweredWords = new ArrayList<String>();
	private Question mQuestion;
	
	@Override
	public void onStart() {
		super.onStart();
		populateQuestion();

		Button btnAnswer = (Button) findViewById(R.id.btnAnswer);
		btnAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				markAnswer();
				checkForNextQuestion();
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		UserInterface.setActionBar(this,
				(ActionBar) findViewById(R.id.actionbar));

	}

	private void populateQuestion() {
		// get question
		mQuestion = Question.getRandom(this);

		while (mAnsweredWords.contains(mQuestion.getName())) {
			mQuestion = Question.getRandom(this);
		}

		// set label
		TextView lblWord = (TextView) findViewById(R.id.lblWord);
		lblWord.setText(mQuestion.getName());

		// set answers to radios
		RadioButton radio = null;

		ArrayList<String> answers = mQuestion.getAnswers();

		for (int i = 0; i < answers.size(); i++) {
			radio = (RadioButton) findViewById(getResources().getIdentifier(
					"radio" + i, "id", getPackageName()));
			radio.setText(answers.get(i));
		}
	}
	
	private void checkForNextQuestion() {
		if (mAnsweredCount == NUMBER_OF_QUESTIONS) {
			Intent intent = buildResultActivityIntent();
			resetCounters();
			startActivityForResult(intent, 0);
		} else {
			// move to next question
			populateQuestion();
		}
	}

	private void markAnswer() {
		RadioGroup rg = (RadioGroup) findViewById(R.id.rgAnswers);
		RadioButton rdSelectedAnswer = (RadioButton) findViewById(rg
				.getCheckedRadioButtonId());
		String ansText = rdSelectedAnswer.getText().toString();


		if (ansText.equals(mQuestion.getDefinition())) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Correct! You beast!", 5);
			toast.show();

			mCorrectlyAnsweredCount++;
		}

		mAnsweredCount++;
		mAnsweredWords.add(mQuestion.getName());
	}
	
	private Intent buildResultActivityIntent() {
		Intent myIntent = new Intent(getApplicationContext(),
				ResultActivity.class);
		Bundle b = new Bundle();

		b.putInt("SCORE", mCorrectlyAnsweredCount);
		b.putInt("QUESTION_COUNT", mAnsweredCount);
		myIntent.putExtras(b);
		return myIntent;
	}

	private void resetCounters() {
		mAnsweredCount = 0;
		mCorrectlyAnsweredCount = 0;
		mAnsweredWords.clear();
	}

}