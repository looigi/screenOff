package com.looigi.screenoff;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class MyWidgetIntentReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals("com.looigi.screenoff.AGGIORNA")){
			Aggiorna(context);
		}
	}

	private void Aggiorna(Context context) {
		boolean active = VariabiliGlobali.getInstance().getDevicePolicyManager().isAdminActive(VariabiliGlobali.getInstance().getCompName());

		if (active) {
			VariabiliGlobali.getInstance().getDevicePolicyManager().lockNow();
		} else {
			Toast.makeText(context,
					"You need to enable the Admin Device Features", Toast.LENGTH_SHORT).show();
		}

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
		remoteViews.setOnClickPendingIntent(R.id.imgAggiorna, Widgets1.buildButtonPendingIntent(context));

		Widgets1.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
	}
}
