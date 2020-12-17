package ru.kvaga.telegram.sendmessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import ru.kvaga.telegram.sendmessage.TelegramSendMessageException.SendMessageException;
import ru.kvaga.telegram.sendmessage.TelegramSendMessageException.UnsupportedParameterException;

public class TelegramSendMessage {
	public static String LINEBREAK="%0A";
	
	public static int PARSE_MODE_HTML=1;
	public static int PARSE_MODE_NONE=2;
	public static int WEB_PAGE_PREVIEW_DISABLE=3;
	public static int WEB_PAGE_PREVIEW_ENABLE=4;
	
	private String channelToken;
	private String channelName;
//	private String URLTemplate = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&parse_mode=html&disable_web_page_preview=None";
	private String URLTemplate = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s";
	private String textTemplate = "&text=%s";
	private String URL;;

	public TelegramSendMessage(String channelToken, String channelName) {
		this.channelToken = channelToken;
		this.channelName = channelName;
		URLTemplate+="&parse_mode=html";
		this.URL = String.format(URLTemplate, this.channelToken, this.channelName);
	}
	
	public TelegramSendMessage(String channelToken, String channelName, int parse_mode, int web_page_preview_mode) throws UnsupportedParameterException {
		this.channelToken = channelToken;
		this.channelName = channelName;
		
		if(parse_mode==PARSE_MODE_HTML) {
			URLTemplate+="&parse_mode=html";
		}else if(parse_mode==PARSE_MODE_NONE) {
			;
		}else {
			throw new TelegramSendMessageException.UnsupportedParameterException("\n"
					+ "For public TelegramSendMessage(String channelToken, String channelName, int parse_mode, int web_page_preview_mode)  initialization you can use only the following parameters:\n"
					+ "in case of parse_mode are allowed: TelegramSendMessage.PARSE_MODE_HTML or TelegramSendMessage.PARSE_MODE_NONE \n"
					);
		}
		
		if(web_page_preview_mode==WEB_PAGE_PREVIEW_DISABLE) {
			URLTemplate+="&disable_web_page_preview=True";
		}else if(web_page_preview_mode==WEB_PAGE_PREVIEW_ENABLE) {
			;
		}else {
			throw new TelegramSendMessageException.UnsupportedParameterException("\n"
					+ "For public TelegramSendMessage(String channelToken, String channelName, int parse_mode, int web_page_preview_mode)  initialization you can use only the following parameters:\n"
					+ "in case of web_page_preview_mode: TelegramSendMessage.WEB_PAGE_PREVIEW_DISABLE or TelegramSendMessage.WEB_PAGE_PREVIEW_ENABLE \n"
					);
		}
		
		
		this.URL = String.format(URLTemplate, this.channelToken, this.channelName);
	}
	

	public void sendMessage(String text) throws Exception  {
		String _url = URL;
		_url = _url + String.format(textTemplate, text);
//		System.out.println(String.format(_url, text)) ;

		try {
			URL url = new URL(_url);
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
						String.format("Couldn't send message for URL: %s. Reponse: %s", _url, response));
			}
		} catch (Exception e) {
			throw new Exception(String.format("Couldn't send message for URL: %s. ", _url) + e.getMessage());
		}
	}

}
