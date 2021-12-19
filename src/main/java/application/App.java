package application;

import fetchInfo.Fetch_Info;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class App {

	private static final Logger LOGGER = LogManager.getLogger(App.class);

	public static void main(String[] args) throws IOException {
		BufferedReader bufRead = new BufferedReader(
			new FileReader("src/token.txt")
		);
		DiscordApi api = new DiscordApiBuilder()
			.setToken(bufRead.readLine())
			.login()
			.join();
		LOGGER.info("Successfully Initialized Server.");
		api.addListener(new Greet());
		api.addListener(new Help());
		api.addListener(new Fetch_Info());
		bufRead.close();
	}
}
