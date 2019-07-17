package com.looigi.screenoff;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class Widgets1 extends AppWidgetProvider {
    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager,
                         final int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

		// RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
		// remoteViews.setOnClickPendingIntent(R.id.imgAggiorna, buildButtonPendingIntent(context));
		
        dostuff(context, appWidgetManager, appWidgetIds);
		
		// pushWidgetUpdate(context, remoteViews);
    }
	
    @Override
    public void onReceive (final Context context, Intent intent) {
    	super.onReceive(context, intent);

 		final AppWidgetManager awm= AppWidgetManager.getInstance(context);
		final int[] appWidgetIds=awm.getAppWidgetIds(new ComponentName(context,Widgets1.class));
		
		onUpdate(context, awm, appWidgetIds);
    }

    private void dostuff(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;

		// Perform this loop procedure for each App Widget that belongs to this provider
		for (int i=0; i<N; i++) {
			int appWidgetId = appWidgetIds[i];

			// Create an Intent to launch ExampleActivity
			Intent intent = new Intent(context, Spegne.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

			// Get the layout for the App Widget and attach an on-click listener
			// to the button
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
			views.setOnClickPendingIntent(R.id.imgAggiorna, pendingIntent);

			// Tell the AppWidgetManager to perform an update on the current app widget
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
    }

	public static PendingIntent buildButtonPendingIntent(Context context) {
		Intent intent = new Intent();
	    intent.setAction("com.looigi.screenoff.AGGIORNA");
	    return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
		ComponentName myWidget = new ComponentName(context, Widgets1.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(myWidget, remoteViews);		
	}

	@Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        Toast.makeText(context, "E' stato bello... Alla prossima...", Toast.LENGTH_SHORT)
                        .show();
    }
}
