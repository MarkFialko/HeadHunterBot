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
    }});


    private final HashMap<String,String> buttons;

    Keyboard(HashMap<String,String> buttons) {
        this.buttons = buttons;
    }

    public HashMap<String, String> getButtons() {
        return this.buttons;
    }
}
