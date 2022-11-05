import entity.plugs.Plugs;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * тест пользователя
 */
public class UserTests {
    @Test
    /**
     * тест второго пользователя
     */
    public void firstUserInfoTest() throws FileNotFoundException {
        assertEquals("ID: 2\n" +
                "ФИО: Иван Иванов Сергеевич \n" +
                "Телефон: +7 (922) 163-04-11\n" +
                "Почта: ivanov@gmail.com", Plugs.getUsers().get(1).getInfo());
    }
    /**
     * тест третьего пользователя
     */
    @Test
    public void secondUserInfoTest() throws FileNotFoundException {
        assertEquals("ID: 3\n" +
                "ФИО: Петя Альваров Иванович \n" +
                "Телефон: +7 (902) 165-56-23\n" +
                "Почта: alvarov@gmail.com", Plugs.getUsers().get(2).getInfo());
    }

}
