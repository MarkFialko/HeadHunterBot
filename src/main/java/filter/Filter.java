package filter;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
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
     * @param filterList
     */
    public void setFilters(List<Pair<String, String>> filterList) {
        for (Pair<String, String> filterPair : filterList) {
            this.filters.put(filterPair.getKey(), filterPair.getValue());
        }
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
