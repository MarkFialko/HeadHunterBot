import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import server.HttpNanoServer;
import service.MessageReceiver;
import service.MessageSender;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class App {

    private static final int PRIORITY_FOR_SENDER = 1;
    private static final int PRIORITY_FOR_RECEIVER = 3;


    public static void main(String[] args) throws TelegramApiException, IOException, ExecutionException, InterruptedException {
        try {
            HttpNanoServer httpNanoServer = new HttpNanoServer();

            HeadHunterBot headHunterBot = new HeadHunterBot(httpNanoServer);

            MessageReceiver messageReceiver = new MessageReceiver(headHunterBot);
            MessageSender messageSender = new MessageSender(headHunterBot);

            headHunterBot.botConnect();

            Thread receiver = new Thread(messageReceiver);
            receiver.setDaemon(true);
            receiver.setName("MsgReciever");
            receiver.setPriority(PRIORITY_FOR_RECEIVER);
            receiver.start();

            Thread sender = new Thread(messageSender);
            sender.setDaemon(true);
            sender.setName("MsgSender");
            sender.setPriority(PRIORITY_FOR_SENDER);
            sender.start();

        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }


    }
}
