package entity;

import fi.iki.elonen.NanoHTTPD;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import server.HttpNanoServer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Класс телеграм бота
 */
public class HeadHunterBot extends TelegramLongPollingBot {
    private HttpNanoServer server;

    public HeadHunterBot() {

    }

    public HttpNanoServer getServer() {
        return server;
    }

    public HeadHunterBot(HttpNanoServer server) {
        this.server = server;
    }

    private final String BOT_NAME = "@HeadHunterURFUBot";
    private final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private final int RECONNECT_PAUSE = 1000;

    public final Queue<Object> sendQueue = new ConcurrentLinkedQueue<>();
    public final Queue<Object> receiveQueue = new ConcurrentLinkedQueue<>();


    @Override
    public void onUpdateReceived(Update update) {
        receiveQueue.add(update);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void botConnect() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
}