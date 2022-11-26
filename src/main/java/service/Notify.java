package service;

import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Класс для отправки уведомлений
 */
public class Notify implements Runnable {
    private static final int MILLISEC_IN_SEC = 1000;
    private static final int TIME_TO_NOTIFY = 5000;

    HeadHunterBot bot;
    String chatID;

    public Notify(HeadHunterBot bot, String chatID) {
        this.bot = bot;
        this.chatID = chatID;
    }

    /**
     * Метод отправляет в очередь на отправку сообщение с уведомлением через какое-то время
     */
    @Override
    public void run() {
        bot.sendQueue.add(getFirstMessage());
            try {
                Thread.sleep(TIME_TO_NOTIFY);
                bot.sendQueue.add(getNotify());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
    }
    private SendMessage getFirstMessage() {
        return new SendMessage(chatID,"Уведомление о новых вакансиях придёт через " + TIME_TO_NOTIFY / MILLISEC_IN_SEC + " секунд.");
    }
    private SendMessage getNotify() {
        return new SendMessage(chatID, "Уведомление о новых вакансиях.");
    }


}
