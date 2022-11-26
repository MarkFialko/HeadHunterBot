import com.fasterxml.jackson.core.JsonProcessingException;
import entity.HeadHunterBot;
import entity.Vacancy;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.MessageReceiver;
import service.MessageSender;

public class App {
    private static final int PRIORITY_FOR_SENDER = 1;
    private static final int PRIORITY_FOR_RECEIVER = 3;


    public static void main(String[] args) throws TelegramApiException, JsonProcessingException {
        HeadHunterBot headHunterBot = new HeadHunterBot() ;

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
    }
}
