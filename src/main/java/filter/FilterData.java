package filter;

/**
 * Enum для отправки сообщений у вакансий
 */
public enum FilterData {
    FILTER_REGION("Выберите регион для поиска вакансий."),
    FILTER_AREA("Выберите профессию для поиска вакансий."),
    FILTER_SPECIALIZATION("Выберите график для поиска вакансий."),
    FILTER_SCHEDULE("Выберите опыт работы."),
    FILTER_EXPERIENCE("Выберите размер заработной платы."),
    FILTER_SALARY("Нажмите кнопку, чтобы посмотреть вакансии."),

    FILTER_GET("Найденные вакансии:\n\n"),
    FILTER_ERROR("Некорректные данные. Попробуйте заного.");

    private String value;

    FilterData(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
