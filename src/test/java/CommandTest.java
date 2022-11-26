import command.HelpCommand;
import command.StartCommand;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;

/**
 * тест пользователя
 */
public class CommandTest {
    @Test
    /**
     * тест для описания команды помощи
     */
    public void HelpDescription() {
        assertEquals("Для получения справки по конкретной команде,нажмите на кнопку с командой, либо введите /help [COMMAND], где COMMAND - интересующая Вас команда.", new HelpCommand().getDescription());
    }

    @Test
    /**
     * тест для описания команды старта
     */
    public void StartDescription() {
        assertEquals("Привет! Этот бот создан для удобного отслеживания вакансий с сайта hh.ru.\n" +
                "Бот позволяет добавлять вакансии в избранное, получать уведомления и отслеживать свой профиль.\n" +
                "Нажмите кнопку \"Помощь\" или введите /help для получения справки.", new StartCommand().getDescription());
    }


}