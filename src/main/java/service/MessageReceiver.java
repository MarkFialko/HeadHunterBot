package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.Command;
import command.ParsedCommand;
import entity.HeadHunterBot;
import entity.UserHH;
import handler.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import command.Parser;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Класс обработчика полученных сообщений
 */
public class MessageReceiver implements Runnable {
    public UserHH user = new UserHH();

    private AbstractHandler vacanciesHandler = new VacanciesHandler(new HeadHunterBot());
    private final int WAIT_FOR_NEW_MESSAGE_DELAY = 1000;
    private HeadHunterBot bot;
    private Parser parser;

    public MessageReceiver(HeadHunterBot bot) {
        this.bot = bot;
        parser = new Parser();
    }

    /**
     * Метод для бесконечного приема сообщения от телеграма и его обработку
     */
    @Override
    public void run() {
        while (true) {
            for (Object object = bot.receiveQueue.poll(); object != null; object = bot.receiveQueue.poll()) {
                try {
                    analyze(object);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(WAIT_FOR_NEW_MESSAGE_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /**
     * Проверка на тип полученного объекта
     *
     * @param object
     * @throws JsonProcessingException
     */
    private void analyze(Object object) throws IOException, ExecutionException, InterruptedException {
        if (object instanceof Update) {
            Update update = (Update) object;
            analyzeForUpdateType(update);
        }
    }

    /**
     * Метод отвечает за вызов метода у подходящего хэндлера
     *
     * @param update эвент телеграма
     * @throws JsonProcessingException
     */
    private void analyzeForUpdateType(Update update) throws IOException, ExecutionException, InterruptedException {

        Long chatId = 0L;
        String inputText = "";

        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
            inputText = update.getMessage().getText();
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            inputText = update.getCallbackQuery().getData();
        }


        ParsedCommand parsedCommand = parser.getParsedCommand(inputText);

        AbstractHandler handlerForCommand = getHandlerForCommand(parsedCommand.getCommand());

        handlerForCommand.operate(chatId.toString(), parsedCommand, update);

    }

    /**
     * @param command команда
     * @return хендлер для команды
     */
    private AbstractHandler getHandlerForCommand(Command command) {
        if (command == null) {
            return new DefaultHandler(bot);
        }
        return switch (command) {
            case START, HELP -> new SystemCommandsHandler(bot);
            case VACANCIES -> vacanciesHandler.setBot(bot);
            case USER -> new UserHandler(bot);
            case ADDTOFAVOURITE -> new FavouriteHandler(bot);
            case FAVOURITES -> new ShowFavouriteHandler(bot);
            case DELETEFAVOURITE -> new DeleteFavouriteHandler(bot);
            case AUTH -> new AuthHandler(bot);
            default -> new DefaultHandler(bot);
        };
    }
}
