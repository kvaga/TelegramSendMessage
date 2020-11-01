package ru.kvaga.telegram.sendmessage;

public class TelegramSendMessageException extends Exception{
	private TelegramSendMessageException(String message) {
		super(message);
	}
	
	public static class SendMessageException extends TelegramSendMessageException {
		public SendMessageException(String message) {
			super(message);
		}
	}
}
