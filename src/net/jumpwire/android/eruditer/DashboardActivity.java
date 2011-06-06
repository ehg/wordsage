package net.jumpwire.android.eruditer;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DashboardActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setHomeLogo(R.drawable.logo);
		
		final Action otherAction = new IntentAction(this, new Intent(this, DashboardActivity.class), R.drawable.ic_title_home_default);
        actionBar.addAction(otherAction);

		ImageButton ibtnTest = (ImageButton)findViewById(R.id.ibtn_test);
		ibtnTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
		    	startActivityForResult(myIntent, 0);
				
			}
		});
		
		ImageButton ibtnAdd= (ImageButton)findViewById(R.id.ibtn_add);
		ibtnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getApplicationContext(), AddWordActivity.class);
		    	startActivityForResult(myIntent, 0);
				
			}
		});
	}
}
