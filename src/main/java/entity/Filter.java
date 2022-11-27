package entity;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс фильтра для запросов к API
 */
public class Filter {

    private final HashMap<String, String> filters = new HashMap<>();

    public HashMap<String, String> getFilters() {
        return filters;
    }

    /**
     * Очищает фильтр после запроса
     */
    public void removeFilter() {
        this.filters.clear();
    }

    /**
     * Устанавливает пару ключа и значения в запросе
     *
     * @param filter
     */
    public void setFilters(Pair<String, String> filter) {
        this.filters.put(filter.getKey(), filter.getValue());
    }

    /**
     * @return Строка для отправки к API
     */
    public String getStringFilters() {
        StringBuilder filter = new StringBuilder("?");

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            filter.append(key)
                    .append("=")
                    .append(value)
                    .append("&");
        }
        return filter.toString();
    }
}
