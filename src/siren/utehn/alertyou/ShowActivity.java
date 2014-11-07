package siren.utehn.alertyou;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class ShowActivity extends ActionBarActivity {

	private TextView tvMsg;
	private String pid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_act);

		tvMsg = (TextView) findViewById(R.id.textView1);

		Bundle extras = getIntent().getExtras();
		String msg, id;

		if (extras != null) {
			msg = extras.getString("msg");
			pid = extras.getString("id");

			tvMsg.setText(msg);

		}

	}

}
