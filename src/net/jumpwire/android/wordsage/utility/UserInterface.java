package net.jumpwire.android.wordsage.utility;

import net.jumpwire.android.wordsage.DashboardActivity;
import net.jumpwire.android.wordsage.R;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class UserInterface {
	public static void setActionBar(Context context, ActionBar actionBar) {
		actionBar.setHomeLogo(R.drawable.logo);
		Intent i = new Intent(context, DashboardActivity.class);
		//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		final Action otherAction = new IntentAction(context, i, R.drawable.ic_title_home_default);
        actionBar.addAction(otherAction);
	}
	
	public static void showToast(Context context, String msg)
	{
		Toast toast = Toast.makeText(context, msg, 5);
		toast.show();
	}
}
