import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ru.kvaga.telegram.sendmessage.TelegramSendMessage;
import ru.kvaga.telegram.sendmessage.TelegramSendMessageException.SendMessageException;

public class Exec {

	private static String filePath="TelegramSendMessage.env";
	static String token;
	static String channelName;
	static String channelId;
	
	public static void main(String[] args) throws Exception {
		getParameters(filePath);
		TelegramSendMessage telegramSendMessage = new TelegramSendMessage(token, channelName);
		telegramSendMessage.sendMessage("qqq");
	}
	
	private static void getParameters(String filePath) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(new File(filePath)));
		token=props.getProperty("token");
		System.out.println("token="+token);
		channelName=props.getProperty("channelName");
		System.out.println("channelName="+channelName);

	}
}
