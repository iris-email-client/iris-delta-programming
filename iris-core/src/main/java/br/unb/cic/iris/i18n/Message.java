package br.unb.cic.iris.i18n;

import java.util.ResourceBundle;

public class Message {

	ResourceBundle rb;

	private static Message instance;

	public Message() {
		rb = ResourceBundle.getBundle("MessageBundle");
	}

	public static Message instance() {
		if (instance == null) {
			instance = new Message();
		}
		return instance;
	}

	public String getMessage(String key) {
		return rb.getString(key);
	}

	public String getMessage(String key, Object... args) {
		return String.format(getMessage(key), args);
	}

	public static String message(String key) {
		return Message.instance().getMessage(key);
	}

	public static String message(String key, Object... args) {
		return Message.instance().getMessage(key, args);
	}
}