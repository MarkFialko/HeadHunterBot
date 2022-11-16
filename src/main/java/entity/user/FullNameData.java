package entity.user;
/**
 * enum для лучшего вывода ФИО пользователя
 */
public enum FullNameData {
    INFO("ФИО: ");

    private final String value;

    FullNameData(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
