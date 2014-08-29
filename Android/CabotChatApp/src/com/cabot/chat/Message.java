package com.cabot.chat;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8136994035787071864L;
	private String sender;
	private String message;
	private boolean isMine;
	
	public Message(String sender, String message, boolean isMine) {
		this.sender = sender;
		this.message = message;
		this.isMine  = isMine;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

}
