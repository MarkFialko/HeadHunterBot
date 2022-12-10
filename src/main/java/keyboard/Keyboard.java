package keyboard;


import java.util.HashMap;

/**
 * Enum с информацией о кнопках команд
 */
public enum Keyboard {
    HELP_KEYBOARD(new HashMap<String, String>() {{
        put("Уведомления", "/help notify");
        put("Вакансии", "/help vacancies");
        put("Пользователь", "/help user");
    }}),
    START_KEYBOARD(new HashMap<String, String>() {{
        put("Помощь", "/help");
    }}),

    VACANCIES_KEYBOARD(new HashMap<String, String>() {{
        put("Регион", "/vacancies area");
    }}),
    VACANCIES_AREA_KEYBOARD(new HashMap<String, String>() {{
        put("Екатеринбург", "/vacancies area=3");
        put("Москва", "/vacancies area=1");
        put("Санкт-Петербург", "/vacancies area=2");
    }}),

    VACANCIES_PROFESSION_KEYBOARD(new HashMap<String, String>() {{
        put("Программист", "/vacancies specialization=1&text=Программист");
        put("Учитель", "/vacancies specialization=14&text=Учитель");
        put("Курьер", "/vacancies specialization=4&text=Курьер");
    }}),

    VACANCIES_SCHEDULE_KEYBOARD(new HashMap<String, String>() {{
        put("Полная занятость", "/vacancies schedule=fullDay");
        put("Сменный график", "/vacancies schedule=shift");
        put("Гибкий график", "/vacancies schedule=flexible");
        put("Удаленная работа", "/vacancies schedule=remote");
    }}),

    VACANCIES_EXPERIENCE_KEYBOARD(new HashMap<String, String>() {{
        put("Нет опыта", "/vacancies experience=noExperience");
        put("От 1 года до 3 лет", "/vacancies experience=between1And3");
        put("От 3 до 6 лет", "/vacancies experience=between3And6");
        put("Более 6 лет", "/vacancies experience=moreThan6");
    }}),

    VACANCIES_SALARY_KEYBOARD(new HashMap<String, String>() {{
        put("Не имеет значения", "/vacancies salary_from=0&only_with_salary=true");
        put("От 30000 руб.", "/vacancies salary=30000&only_with_salary=true");
        put("От 70000 руб.", "/vacancies salary=70000&only_with_salary=true");
        put("От 100000 руб.", "/vacancies salary=100000&only_with_salary=true");
    }}),

    VACANCIES_GET_KEYBOARD(new HashMap<String, String>() {{
        put("Посмотреть вакансии", "/vacancies get");
    }});


    private final HashMap<String, String> buttons;

    Keyboard(HashMap<String, String> buttons) {
        this.buttons = buttons;
    }

    public HashMap<String, String> getButtons() {
        return this.buttons;
    }
}
