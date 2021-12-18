package fetchInfo;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Path;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Fetch_Info implements MessageCreateListener {

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
			.setDescription("Usage: `mc info <number>`\nExample: `mc info 2`");

		try {
			Path fileName = Path.of("src/assets/info/info_list.txt");
			String content = Files.readString(fileName);
			listEmbed.addField("Available Microcontrollers:", content);
		} catch (Exception e) {}
		String[] strArr = event.getMessage().getContent().split(" ");
		if (strArr.length >= 3) {
			if (
				strArr[0].equalsIgnoreCase("mc") &&
				strArr[1].equalsIgnoreCase("info")
			) {
				event.getChannel().sendMessage(getInfo(strArr[2]));
			}
		}
		if (strArr.length >= 2) {
			if (
				strArr[0].equalsIgnoreCase("mc") &&
				strArr[1].equalsIgnoreCase("list")
			) {
				event.getChannel().sendMessage(listEmbed);
			}
		}
	}
}
