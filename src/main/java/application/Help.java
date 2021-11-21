package application;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class Help implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event){
        EmbedBuilder helpEmbed = new EmbedBuilder();
        helpEmbed.setTitle("**Help Page**")
                    .setDescription("Here is the list of commands!\nUsage: `mp <command>`");

        if(event.getMessage().getContent().equals("mp help")){
            event.getChannel().sendMessage(helpEmbed);
        }
    }
}
