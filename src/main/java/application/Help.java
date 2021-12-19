package application;

import java.awt.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Help implements MessageCreateListener {

	private static final Logger LOGGER = LogManager.getLogger(Help.class);

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		EmbedBuilder helpEmbed = new EmbedBuilder();
		helpEmbed
			.setColor(Color.BLUE)
			.setTitle("**Help Page**")
			.setDescription(
				"Here is the list of commands!\nUsage: `mc <command>`"
			)
			.addField("**Help**", "`mc help`  Displays this help")
			.addField(
				"**List Microcontrollers**",
				"`mc list`  Lists all the avalaible microcontrollers"
			)
			.addField(
				"**Get Information about a certain microcontroller**",
				"`mc info <name>`  Fetches detailed and comprehensive information about a certain microcontroller\nExample: `mp info <1-10>`"
			)
			.addField("**Say Hi to me!**", "`hi` or `hello`");

		if (event.getMessage().getContent().equalsIgnoreCase("mc help")) {
			LOGGER.info(
				"In " +
				event.getChannel() +
				event.getMessageAuthor().getDiscriminatedName() +
				" issued a help command"
			);
			event.getChannel().sendMessage(helpEmbed);
		}
	}
}
