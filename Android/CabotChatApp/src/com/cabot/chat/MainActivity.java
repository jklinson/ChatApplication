package com.cabot.chat;

import com.example.socketio.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	String strUserName;
	EditText edtUserName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		edtUserName = (EditText) findViewById(R.id.edt_user_name);
		Button btnOK = (Button) findViewById(R.id.btn_ok);

		btnOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				strUserName = edtUserName.getText().toString();
				if (strUserName != null && strUserName.length() > 0 || !"".equals(strUserName)) {
					Intent intent = new Intent(getApplicationContext(), ScreenChat.class);
					intent.putExtra("UserName", strUserName);
					startActivity(intent);
					edtUserName.getText().clear();
				}else {
					Toast.makeText(getApplicationContext(), "user name null", Toast.LENGTH_SHORT).show();
					return;
				}
			}
		});
	}
}
