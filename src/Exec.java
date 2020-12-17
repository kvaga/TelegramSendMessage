import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ru.kvaga.telegram.sendmessage.TelegramSendMessage;
import ru.kvaga.telegram.sendmessage.TelegramSendMessageException.SendMessageException;

public class Exec {

	// Specify a configuration file with token and channelName information
	private static String filePath="TelegramSendMessage.env";
	static String token;
	static String channelName;
	
	public static void main(String[] args) throws Exception {
		getParameters(filePath);
		System.out.print("Sending message ... ");
//		TelegramSendMessage telegramSendMessage = new TelegramSendMessage(token, channelName);
		TelegramSendMessage telegramSendMessage = new TelegramSendMessage(
				token, 
				channelName, 
				TelegramSendMessage.PARSE_MODE_HTML, 
				TelegramSendMessage.WEB_PAGE_PREVIEW_DISABLE);

		for(int i=0;i<1;i++) {
//			telegramSendMessage.sendMessage("Some text <a href='https://ya.ru'>link</a> some other text"+i);
			telegramSendMessage.sendMessage("Stock: <a href='https://tinkoff.ru/invest/stocks/YNDX/'>link</a> some other text");
		}
		System.out.println("[OK]. The message was sent");
	}
	
	private static void getParameters(String filePath) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(new File(filePath)));
		
		System.out.print(String.format("Reading information from the %s configuration file ... ", filePath));
		token=props.getProperty("token");
		System.out.print(String.format("token=%s ", token));
		channelName=props.getProperty("channelName");
		System.out.println(String.format("channelName=%s ", channelName));
	}
}
