package net.jumpwire.android.wordsage;

import net.jumpwire.android.wordsage.utility.UserInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.markupartist.android.widget.ActionBar;

public class DashboardActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		UserInterface.setActionBar(this,
				(ActionBar) findViewById(R.id.actionbar));

		ImageButton ibtnTest = (ImageButton) findViewById(R.id.ibtn_test);
		ImageButton ibtnAdd = (ImageButton) findViewById(R.id.ibtn_add);
		ImageButton ibtnList = (ImageButton) findViewById(R.id.ibtn_list);

		ibtnTest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getApplicationContext(),
						TestActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});

		ibtnAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getApplicationContext(),
						AddWordActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});

		ibtnList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getApplicationContext(),
						ListWordActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
	}

}
