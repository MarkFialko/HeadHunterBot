package service;


import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Класс обработчика сообщений, которые должны быть отправлены пользователю
 */
public class MessageSender implements Runnable {
    private final int SENDER_SLEEP_TIME = 1000;
    private HeadHunterBot bot;

    public MessageSender(HeadHunterBot bot) {
        this.bot = bot;
    }

    /**
     * Метод для бесконечной обработки очереди входящих сообщений
     */
    @Override
    public void run() {
        try {
            while (true) {
                for (Object object = bot.sendQueue.poll(); object != null; object = bot.sendQueue.poll()) {
                    send(object);
                }
                try {
                    Thread.sleep(SENDER_SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для отправки сообщения
     * @param object
     */
    private void send(Object object) {
        try {
            MessageType messageType = messageType(object);
            switch (messageType) {
                case EXECUTE:
                    BotApiMethod<Message> message = (BotApiMethod<Message>) object;
                    bot.execute(message);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MessageType messageType(Object object) {
        if (object instanceof BotApiMethod) return MessageType.EXECUTE;
        return MessageType.NOT_DETECTED;
    }

    enum MessageType {
        EXECUTE, NOT_DETECTED,
    }

}
