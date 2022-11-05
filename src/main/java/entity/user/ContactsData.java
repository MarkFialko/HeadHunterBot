package entity.user;

/**
 * enum для лучшего вывода контактных данных пользователя
 */
public enum ContactsData {
    PHONE("Телефон: "),
    EMAIL("Почта: ");

    private final String value;

    ContactsData(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
