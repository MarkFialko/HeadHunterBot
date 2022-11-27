package keyboard;


import java.util.HashMap;

/**
 * Enum с информацией о кнопках команд
 */
public enum Keyboard {
    HELP_KEYBOARD(new HashMap<String, String>() {{
        put("Помощь", "/help help");
        put("Старт", "/help start");
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
        put("Программист", "/vacancies specialization=1");
        put("Учитель", "/vacancies specialization=14");
        put("Курьер", "/vacancies specialization=4");
    }}),

    VACANCIES_SCHEDULE_KEYBOARD(new HashMap<String, String>() {{
        put("Полная занятость", "/vacancies schedule=fullDay");
        put("Сменный график", "/vacancies schedule=shift");
        put("Гибкий график", "/vacancies schedule=flexible");
        put("Удаленная работа", "/vacancies schedule=remote");
    }}),

    VACANCIES_GET_KEYBOARD(new HashMap<String, String>() {{
        put("Посмотреть вакансии", "/vacancies get");
    }});


    private final HashMap<String,String> buttons;

    Keyboard(HashMap<String,String> buttons) {
        this.buttons = buttons;
    }

    public HashMap<String, String> getButtons() {
        return this.buttons;
    }
}
