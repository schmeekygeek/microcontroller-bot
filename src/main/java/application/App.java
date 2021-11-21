package application;

import java.io.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class App{
    public static void main(String[] args) throws IOException{
        BufferedReader bufRead = new BufferedReader(new FileReader("token.txt"));
        
        DiscordApi api = new DiscordApiBuilder()
          .setToken(bufRead.readLine())
          .login().join();
        api.addListener(new Greet());
        api.addListener(new Help());
        bufRead.close();
    }
}
