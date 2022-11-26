package keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для создания клавиатуры телеграм бота
 */
public class KeyboardCreator {
    /**
     *
     * @param columnCount - количество колонок в клавиатуре
     * @param buttons - Хэшмап с названиями кнопок и колбэк датой на них
     * @return
     */
    public static InlineKeyboardMarkup getInlineKeyboard(Integer columnCount, HashMap<String, String> buttons) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        var keyboardRow = new ArrayList<InlineKeyboardButton>();
        int count = 0;
        for (Map.Entry<String, String> entry : buttons.entrySet()) {
            String buttonName = entry.getKey();
            String buttonData = entry.getValue();
            if (count >= columnCount) {
                keyboard.add(new ArrayList<>(keyboardRow));
                keyboardRow.clear();
                count = 0;
            }
            keyboardRow.add((createButton(buttonName, buttonData)));
            count++;
        }

        if (!keyboardRow.isEmpty()) {
            keyboard.add(keyboardRow);
        }
        return new InlineKeyboardMarkup(keyboard);
    }

    /**
     *
     * @param name имя кнопки
     * @param data - колбэк на кнопке
     * @return кнопку для телеграм
     */
    private static InlineKeyboardButton createButton(String name, String data) {

        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText(name);
        button.setCallbackData(data);

        return button;
    }

}
