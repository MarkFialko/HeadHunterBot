import entity.filter.Filter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * тест вакансий
 */
public class VacancyFilterTests {
    @Test
    /**
     * тест первого учителя
     */
    public void filterFirstTeacherTest() throws FileNotFoundException {

        assertEquals("ID: 2\n" +
                "Название: Учитель\n" +
                "Телефон: +7 (922) 000-76-40\n" +
                "Почта: teacher@mail.ru\n" +
                "Ключевые навыки:\n" +
                " -> Усидчивость\n" +
                " -> Опытность\n", new Filter().filterVacancy("name=Учитель").get(0).getInfo());
    }
    @Test
    /**
     * тест второго учителя
     */
    public void filterSecondTeacherTest() throws FileNotFoundException {

        assertEquals("ID: 4\n" +
                "Название: Учитель\n" +
                "Телефон: +7 (901) 823-02-23\n" +
                "Почта: teacher2@mail.ru\n" +
                "Ключевые навыки:\n" +
                " -> Ничего не надо\n", new Filter().filterVacancy("name=Учитель").get(1).getInfo());
    }
    /**
     * тест программиста и id
     */
    @Test
    public void filterDeveloperTest() throws FileNotFoundException {

        assertEquals("ID: 1\n" +
                "Название: Программист\n" +
                "Телефон: +7 (912) 202-64-40\n" +
                "Почта: bestwork@mail.ru\n" +
                "Ключевые навыки:\n" +
                " -> Коммуникабельность\n" +
                " -> Открытость\n" +
                " -> Стрессоустойчивость\n", new Filter().filterVacancy("name=Программист&id=1").get(0).getInfo());
    }

}
