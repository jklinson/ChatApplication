package com.cabot.chat;

import java.util.ArrayList;

import com.example.socketio.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {
	ArrayList<Message> mMessages;
	Context mContext;
	LayoutInflater mLayoutInflater;
	
	public MessageAdapter(Context context, ArrayList<Message> messages) {
		mContext = context;
		mMessages = messages;
		
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return (mMessages == null ? 0 : mMessages.size());
	}

	@Override
	public Object getItem(int index) {
		if(mMessages == null || mMessages.size() < index) return null;
		return mMessages.get(index);
	}

	@Override
	public long getItemId(int index) {
		return 0;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder = new ViewHolder();
		// Setting image for stamp
		if (view == null) {
			holder = new ViewHolder();
			view = mLayoutInflater.inflate(R.layout.item, null);
			holder.txtSender = (TextView) view.findViewById(R.id.txt_sender);
			holder.txtMessage = (TextView) view.findViewById(R.id.txt_message);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		Message message = mMessages.get(position);
		if(message == null) return view;
		holder.txtMessage.setText("");
		holder.txtSender.setText("");
		holder.txtSender.setText("Sender: " + message.getSender());
		holder.txtMessage.setText(message.getMessage());
		if(position % 2 == 0){
			view.setBackgroundResource(R.color.gray);
		}else {
			view.setBackgroundResource(R.color.green);
		}
		return view;
	}
	
	static class ViewHolder {
		TextView txtSender;
		TextView txtMessage;
	}

}
