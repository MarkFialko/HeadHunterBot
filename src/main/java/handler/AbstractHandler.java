package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Хендлер для обработки комманд
 */
public abstract class AbstractHandler {
    HeadHunterBot bot;

    AbstractHandler(HeadHunterBot bot) {
        this.bot = bot;
    }

    /**
     * Добавляет в очередь новое сообщение
     *
     * @param chatId        чат из телеграма
     * @param parsedCommand - спарсенная команда
     * @param update        - эвент из телеграмма
     * @throws JsonProcessingException
     */
    public abstract void operate(String chatId, ParsedCommand parsedCommand, Update update) throws IOException, ExecutionException, InterruptedException;

    public AbstractHandler setBot(HeadHunterBot bot) {
        this.bot = bot;
        return this;
    }
}
