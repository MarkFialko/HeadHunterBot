package entity.user;

/**
 * класс пользователя
 */
public class User {
    private FullName fullName;
    private Contacts contacts;
    private Integer id;

    public User(FullName fullName, Contacts contacts, Integer id) {
        this.fullName = fullName;
        this.contacts = contacts;
        this.id = id;
    }

    /**
     * получить информацию о пользователе
     * @return Строка со всей информации о пользователе
     */
    public String getInfo() {
        StringBuilder userInfo = new StringBuilder();
        userInfo
                .append("ID: ")
                .append(id)
                .append("\n")
                .append(fullName.getFullName())
                .append("\n")
                .append(contacts.getContacts());

        return userInfo.toString();
    }
}