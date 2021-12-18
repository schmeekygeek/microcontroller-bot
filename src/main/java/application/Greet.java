package application;

import java.util.Random;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Greet implements MessageCreateListener {

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		String[] text = event.getMessage().getContent().split(" ");
		try {
			if (
				text[0].equalsIgnoreCase("hi") ||
				text[0].equalsIgnoreCase("hello")
			) {
				event.getChannel().sendMessage(sayHello());
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
