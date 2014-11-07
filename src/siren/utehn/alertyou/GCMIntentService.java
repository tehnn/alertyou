package siren.utehn.alertyou;

import static siren.utehn.alertyou.CommonUtilities.SENDER_ID;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	public GCMIntentService() {
		super(SENDER_ID);
	}

	private static final String TAG = "gcm";

	@Override
	protected void onRegistered(Context arg0, String registrationId) {
		//Log.i(TAG, "Device registered: regId = " + registrationId);
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		//Log.i(TAG, "unregistered = " + arg1);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		
		int noti_id = (int) (System.currentTimeMillis());
		
		String message = intent.getExtras().getString("message");
		String id = intent.getExtras().getString("id");
		notify1(noti_id,context, message,id);
	}

	@Override
	protected void onError(Context arg0, String errorId) {
		//Log.i(TAG, "Received error: " + errorId);
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		return super.onRecoverableError(context, errorId);
	}

	private static void notify1(int noti_id,Context context, String message,String id) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();

		NotificationManager notifyManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Notification notify = new Notification(icon, message, when);

		String title = context.getString(R.string.app_name);

		Intent itent2show = new Intent(context, ShowActivity.class);
		itent2show.putExtra("msg", message);
		itent2show.putExtra("id", id);

		itent2show.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

		PendingIntent intent = PendingIntent.getActivity(context,noti_id,itent2show,PendingIntent.FLAG_CANCEL_CURRENT);

		notify.setLatestEventInfo(context, title, message, intent);

		notify.flags |= Notification.FLAG_AUTO_CANCEL;
		notify.defaults|=Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE;
		
		notifyManager.notify(noti_id, notify);
	}
}
