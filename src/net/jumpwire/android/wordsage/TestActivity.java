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
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity {
	public static ArrayList<Question> mIncorrectQuestions;
	
	private static final int NUMBER_OF_QUESTIONS = 10;

	private int mAnsweredCount = 0;
	private int mCorrectlyAnsweredCount = 0;
	private ArrayList<String> mAnsweredWords = new ArrayList<String>();
	private Question mQuestion;
	private TextView mScore;
	private OnCheckedChangeListener mOnCheckedChange = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			markAnswer(group);
			checkForNextQuestion();
		}
	};

	@Override
	public void onStart() {
		super.onStart();
		mIncorrectQuestions = new ArrayList<Question>();
		resetCounters();
		populateQuestion();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		UserInterface.setActionBar(this,
				(ActionBar) findViewById(R.id.actionbar));
		mScore = (TextView)findViewById(R.id.lblScore);
	}

	private void populateQuestion() {
		RadioGroup radioGroup = (RadioGroup)findViewById(R.id.rgAnswers);
		radioGroup.setOnCheckedChangeListener(null);
		
		// get question
		mQuestion = Question.getRandom(this);

		while (mAnsweredWords.contains(mQuestion.getName())) {
			mQuestion = Question.getRandom(this);
		}

		// set label
		TextView lblWord = (TextView) findViewById(R.id.lblWord);
		lblWord.setText(mQuestion.getName());

		
		radioGroup.clearCheck();
		radioGroup.setOnCheckedChangeListener(mOnCheckedChange);
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

	private void markAnswer(RadioGroup rg) {
		RadioButton rdSelectedAnswer = (RadioButton) findViewById(rg
				.getCheckedRadioButtonId());
		String ansText = rdSelectedAnswer.getText().toString();

		if (ansText.equals(mQuestion.getDefinition())) mCorrectlyAnsweredCount++;
		else mIncorrectQuestions.add(mQuestion);

		mScore.setText("Score: " + mCorrectlyAnsweredCount + " / " + (mAnsweredCount + 1));
		
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