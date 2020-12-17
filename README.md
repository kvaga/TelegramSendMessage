# TelegramSendMessage
All examples are provided in the Exec.java file

### Versions of jar file:
* telegramsendmessage-1.2.jar - common basic functionality
* telegramsendmessage-1.3.jar - uses &parse_mode=html by default
* telegramsendmessage-1.3.1.jar - fixed line breaking issue
* telegramsendmessage-1.3.2.jar - added line breaker parameter TelegramSendMessage.LINEBREAK (	public static String LINEBREAK="%0A"; )
* telegramsendmessage-1.4.jar - Now for public TelegramSendMessage(String channelToken, String channelName, int parse_mode, int web_page_preview_mode) initialization you can use only the following parameters:
  * in case of parse_mode are allowed:TelegramSendMessage.PARSE_MODE_HTML or TelegramSendMessage.PARSE_MODE_NONE
  * in case of web_page_preview_mode: TelegramSendMessage.WEB_PAGE_PREVIEW_DISABLE or TelegramSendMessage.WEB_PAGE_PREVIEW_ENABLES
					