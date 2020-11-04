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
		TelegramSendMessage telegramSendMessage = new TelegramSendMessage(token, channelName);
		for(int i=0;i<5;i++) {
			telegramSendMessage.sendMessage(""+i);
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
