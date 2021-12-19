package application;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Greet implements MessageCreateListener {

	private static final Logger LOGGER = LogManager.getLogger(Greet.class);

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		String[] text = event.getMessage().getContent().split(" ");
		try {
			if (
				text[0].equalsIgnoreCase("hi") ||
				text[0].equalsIgnoreCase("hello")
			) {
				String greetMessage = sayHello();
				event.getChannel().sendMessage(greetMessage);
				LOGGER.info(
					"In " +
					event.getChannel() + " " + 
					event.getMessageAuthor().getDiscriminatedName() +
					" said " +
					text[0] +
					", replied with " +
					greetMessage
				);
			}
		} catch (Exception exception) {
			System.out.println("Error");
		}
	}

	public static String sayHello() {
		Random randomGenerator = new Random();
		String[] greetings = {
			"Marhaba",
			"Namaskar",
			"Hola",
			"Bonjour",
			"Guten Tag",
			"Shalom",
			"Salve",
			"Konnichiwa, Senpai >~<\"\"",
			"Assalamualaikum",
			"Namaste",
			"Wadakkam",
		};
		return greetings[randomGenerator.nextInt(greetings.length)];
	}
}
