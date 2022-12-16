package auth;

public enum AuthInfo {
    AUTH_ERROR_MESSAGE("Ошибка при авторизации"),
    NEW_USER_FOR_AUTH_MESSAGE("Выполните команду /auth для того, чтобы авторизоваться."),
    OLD_USER_FOR_AUTH_MESSAGE("Пожалуйста, авторизуйтесь заного, используя команду /auth.");

    private final String value;

    AuthInfo(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }


}
