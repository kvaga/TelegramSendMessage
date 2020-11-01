package ru.kvaga.telegram.sendmessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import ru.kvaga.telegram.sendmessage.TelegramSendMessageException.SendMessageException;

public class TelegramSendMessage {
	private String channelToken;
	private String channelName;
	private String URLTemplate = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s";
	private String textTemplate = "&text=%s";
	private String URL;

	public TelegramSendMessage(String channelToken, String channelName) {
		this.channelToken = channelToken;
		this.channelName = channelName;
		this.URL = String.format(URLTemplate, this.channelToken, this.channelName);
	}

	public void sendMessage(String text) throws Exception  {
		URL = URL + String.format(textTemplate, text);
//		System.out.println(String.format(URL, text));

		try {
			URL url = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String s;
			StringBuilder sb = new StringBuilder();
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}

			String response = sb.toString();
			if (!response.contains("\"ok\":true")) {
				throw new TelegramSendMessageException.SendMessageException(
						String.format("Couldn't send message for URL: %s. Reponse: %s", URL, response));
			}
		} catch (Exception e) {
			throw new Exception(String.format("Couldn't send message for URL: %s. ", URL) + e.getMessage());
		}
	}

}
