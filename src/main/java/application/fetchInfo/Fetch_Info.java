package application.fetchInfo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Fetch_Info implements MessageCreateListener {

	private static final Logger LOGGER = LogManager.getLogger(Fetch_Info.class);

	public static String getInfo(String suffix) {
		try {
			int test = Integer.parseInt(suffix);
			Path fileName = Path.of(
				String.format(
					"src/assets/info/info%s.txt",
					Integer.toString(test)
				)
			);
			String content = Files.readString(fileName);

			return content;
		} catch (Exception e) {
			return (
				"The number given isn't valid or the information doesn't exist"
			);
		}
	}

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		EmbedBuilder listEmbed = new EmbedBuilder();
		listEmbed
			.setColor(Color.BLUE)
			.setTitle("**List of Microcontrollers**")
			.setDescription("Usage: `mc info <number>`\nExample: `mc info 2`")
			.setThumbnail(event.getMessageAuthor().getAvatar());

		try {
			Path fileName = Path.of("src/assets/info/info_list.txt");
			String content = Files.readString(fileName);
			listEmbed.addField("Available Microcontrollers:", content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] strArr = event.getMessage().getContent().split(" ");
		if (strArr.length >= 3) {
			if (
				strArr[0].equalsIgnoreCase("mc") &&
				strArr[1].equalsIgnoreCase("info")
			) {
				try {
					FileReader fileReader = new FileReader(
						"src/assets/info/info" + strArr[2] + ".txt"
					);
					BufferedReader bufRead = new BufferedReader(fileReader);

					LOGGER.info(
						"In " +
						event.getChannel() +
						" " +
						event.getMessageAuthor().getDiscriminatedName() +
						" requested info for " +
						strArr[2] +
						" - " +
						bufRead.readLine()
					);
					bufRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				event.getChannel().sendMessage(getInfo(strArr[2]));
			}
		}
		if (strArr.length >= 2) {
			if (
				strArr[0].equalsIgnoreCase("mc") &&
				strArr[1].equalsIgnoreCase("list")
			) {
				event.getChannel().sendMessage(listEmbed);
				LOGGER.info(
					"In " +
					event.getChannel() +
					" " +
					event.getMessageAuthor().getDiscriminatedName() +
					" requested a list of microcontrollers"
				);
			}
		}
	}
}
