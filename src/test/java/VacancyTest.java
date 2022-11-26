import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Vacancy;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * тест пользователя
 */
public class VacancyTest {
    @Test
    /**
     * тест для нахождения вакансии по id 14447
     */
    public void FirstIdTest() throws JsonProcessingException {
        assertEquals("Вакансия: ОФИС-МЕНЕДЖЕР\n" +
                "Сcылка: https://hh.ru/vacancy/14447\n", Vacancy.getVacancy("14447").getInfo());
    }

    ;

    /**
     * тест для нахождения вакансии по id 14448
     */
    @Test
    public void SecondIdTest() throws JsonProcessingException {
        assertEquals("Вакансия: Начальник отдела внутреннего аудита\n" +
                "Сcылка: https://hh.ru/vacancy/14448\n", Vacancy.getVacancy("14448").getInfo());
    }

}