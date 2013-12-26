package net.okjsp.android.gawi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GawiActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn1 = (Button) findViewById(R.id.scissorsBtn);
		Button btn2 = (Button) findViewById(R.id.rockBtn);
		Button btn3 = (Button) findViewById(R.id.paperBtn);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int choice = 0;
		switch (v.getId()) {
		case R.id.scissorsBtn:
			choice = 0;
			break;
		case R.id.rockBtn:
			choice = 1;
			break;
		case R.id.paperBtn:
			choice = 2;
			break;
		}
		Log.i("v", "" + choice);

		playLocal(choice);
	}

	private void playLocal(int choice) {
		String [] items = {getResources().getString(R.string.scissors),
				getResources().getString(R.string.rock),
				getResources().getString(R.string.paper)};

		Random random = new Random(System.currentTimeMillis());
		int comChoice = random.nextInt(3);
		String message = judge(choice, comChoice);
		message += "\nYou: " + items[choice] +
				"\nCom: " + items[comChoice] +
				"\n"; 
		((TextView) findViewById(R.id.textView2)).setText(message);
	}

	public String judge(int choice, int comChoice) {
		String judgement;

		if (choice == comChoice) {
			judgement = getResources().getString(R.string.draw);
		} else {
			int diff = comChoice - choice;
			if ((diff == 1) || (diff == -2)) {
				judgement = getResources().getString(R.string.lose);
			} else {
				judgement = getResources().getString(R.string.win);
			}
		}
		return judgement;
	}

	public void play(int choice) {
		String json = readData(choice);
		StringBuilder message = new StringBuilder();
		try {
			JSONObject jsonArray = new JSONObject(json);
			((TextView) findViewById(R.id.textView1)).setText(jsonArray
					.getString("judgement"));

			JSONObject p1 = jsonArray.getJSONObject("p1");
			JSONObject p2 = jsonArray.getJSONObject("p2");
			message.append(p1.getString("name") + ":" + p1.getString("choice"));
			message.append("\n" + p2.getString("name") + ":"
					+ p2.getString("choice"));

			JSONObject stat = jsonArray.getJSONObject("stat");
			message.append("\n\n" + stat.getString("win") + "-win, ");
			message.append(stat.getString("even") + "-draw, ");
			message.append(stat.getString("lose") + "-lose ");
			message.append("\nTotal: " + stat.getString("total"));
			message.append("\nRate: " + stat.getString("rate"));

			((TextView) findViewById(R.id.textView2)).setText(message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String readData(int choice) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				"http://www.okjsp.pe.kr/gawi/queryJSON.jsp?choice=" + choice);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e("json", "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}