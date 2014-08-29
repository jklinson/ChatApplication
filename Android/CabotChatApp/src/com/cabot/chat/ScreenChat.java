package com.cabot.chat;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.socketio.R;
import com.magnux.iobahn.SocketIO;
import com.magnux.iobahn.SocketIOConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class ScreenChat extends Activity {

	EditText mEditMessage;
	Button mBtnSend;
	ListView mListMessage;
	public static final String TAG = "ChatApplication";
	boolean isConnected;
	public static final String KEY_DEVICE_ID = "sender";
	public static final String KEY_MESSAGE = "message";
	public String device_id;
	MessageAdapter mMessageAdapter;
	String userName;
	ArrayList<Message> mMessages = new ArrayList<Message>();
	private final SocketIO mConnection = new SocketIOConnection();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_chat);
		device_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			userName = extras.getString("UserName");
		}
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mEditMessage = (EditText) findViewById(R.id.txt_message);
		mListMessage = (ListView) findViewById(R.id.list_message);
		mMessageAdapter = new MessageAdapter(this, mMessages);
		mListMessage.setAdapter(mMessageAdapter);
		mMessageAdapter.notifyDataSetChanged();
		mBtnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				MsgEvent message = new MsgEvent();
				message.nickname=userName;
				message.text=mEditMessage.getText().toString();
				try {
					sendMessage(message);
					mEditMessage.getText().clear();					
					mMessages.add(new Message(message.nickname, message.text,true));
					mMessageAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		connectSocket();

	}

	public void scrollMyListViewToBottom() {
		mListMessage.post(new Runnable() {
			@Override
			public void run() {
				// Select the last row so it will scroll into view...
				mMessageAdapter.notifyDataSetChanged();
				mListMessage.setSelection(mMessageAdapter.getCount() - 1);
			}
		});
	}
	public void showAlert(String message){
		AlertDialog.Builder dlg = new AlertDialog.Builder(ScreenChat.this);
		dlg.setMessage(message);
		dlg.setTitle("alert");
		dlg.setCancelable(true);
		dlg.create();
		dlg.show();
	}

	private void connectSocket() {

		final String wsuri = "ws://" + Globals.IP_ADDRESS + ":" + Globals.PORT;



		// we establish a connection by giving the WebSockets URL of the server
		// and the handler for open/close events
		mConnection.connect(wsuri, new SocketIO.ConnectionHandler() {

			@Override
			public void onOpen() {

				// The connection was successfully established. we set the status
				// and save the host/port as Android application preference for next time.
				mConnection.emit("nickname", userName);
				mConnection.on("user message", MsgEvent.class, new SocketIO.EventHandler() {

					public void onEvent(Object event) {

						// when we get an event, we safely can cast to the type we specified previously
						MsgEvent evt = (MsgEvent) event;
						mMessages.add(new Message(evt.nickname, evt.text,false));
						mMessageAdapter.notifyDataSetChanged();
						scrollMyListViewToBottom();
					}
				});
				mConnection.on("nicknames", UserJoinedEvent.class, new SocketIO.EventHandler() {

					public void onEvent(Object event) {

						// when we get an event, we safely can cast to the type we specified previously
						UserJoinedEvent evt = (UserJoinedEvent) event;

						showAlert("Event received : " + evt.toString());
					}
				});
			}

			@Override
			public void onClose(int code, String reason) {

				// The connection was closed. Set the status line, show a message box,
				// and set the button to allow to connect again.
				showAlert(reason);
			}
		});
	}
	private void sendMessage(MsgEvent message) throws JSONException {
		if(mConnection.isConnected()){
			mConnection.emit("user message",message);
		}
		else {
			showAlert("mconnection is not connected");
		}
	}
	/**
	 * We want PubSub events delivered to us in JSON payload to be automatically
	 * converted to this domain POJO. We specify this class later when we subscribe.
	 */
	private static class MsgEvent {

		public String text;
		public String nickname;

		@Override
		public String toString() {
			return "\ntext: " + text +
					"\nnickname: " + nickname;
		}
	}
	private static class UserJoinedEvent {

		public String nickname;

		@Override
		public String toString() {
			return  "\nnickname: " + nickname;
		}
	}

}
