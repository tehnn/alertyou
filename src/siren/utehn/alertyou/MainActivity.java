package siren.utehn.alertyou;

import static siren.utehn.alertyou.CommonUtilities.SENDER_ID;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends ActionBarActivity {

	private String TAG = "gcm";
	private TextView mDisplay;
	String regId = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDisplay = (TextView) findViewById(R.id.textView1);

	}

	private void checkNotNull(Object reference, String name) {
		if (reference == null) {
			throw new NullPointerException(getString(R.string.error_config,name));
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		
	}

	public void genKeys(View v) {
				
		
		
		checkNotNull(SENDER_ID, "SENDER_ID");
		GCMRegistrar.checkDevice(getApplicationContext());
		GCMRegistrar.checkManifest(getApplicationContext());

		regId = GCMRegistrar.getRegistrationId(getApplicationContext()).trim();		
		
		GCMRegistrar.register(getApplicationContext(), SENDER_ID);
		
		mDisplay.setText(regId);

	}
	
	public void UnRegisKeys(){
		GCMRegistrar.unregister(getApplicationContext());
	}

	public void RegisKeys(View v) {		
		
		String gmail = "";
		Account[] accounts = AccountManager.get(getApplicationContext()).getAccountsByType("com.google");
		if(accounts.length > 0) {
		  gmail = accounts[0].name;
		}
		
		new sendIdOnOverServer().execute(gmail,regId);
	}
	
	public void StartApp(View v){
		finish();
		//Intent intent = new Intent(getApplicationContext(),WebActivity.class);
		//startActivity(intent);
		
	}

	public class sendIdOnOverServer extends AsyncTask<String, Void, String> {

		ProgressDialog pd = null;
		String res;

		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(MainActivity.this, "Please wait",
					"Please wait..", true);
			pd.setCancelable(true);

		}

		@Override
		protected String doInBackground(String... params) {
			try {
				HttpResponse response = null;
				HttpParams httpParameters = new BasicHttpParams();
				HttpClient client = new DefaultHttpClient(httpParameters);
				
				String host = "http://epid.plkhealth.go.th/ws/adduser.php";
				String url = host+"?user="+params[0]+"&regID="+ params[1];

				HttpGet request = new HttpGet(url);

				response = client.execute(request);

				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String webServiceInfo = "";
				while ((webServiceInfo = rd.readLine()) != null) {

					res = webServiceInfo;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;

		}

		@Override
		protected void onPostExecute(String result) {
			pd.dismiss();			
			GCMRegistrar.register(getApplicationContext(), SENDER_ID);
			Toast.makeText(getApplicationContext(), result, 10000).show();

		}

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
