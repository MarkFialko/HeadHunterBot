package entity;

/**
 * Класс данных пользваотеля с HH API
 */
public class UserHH {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String resumesUrl;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getResumesUrl() {
        return resumesUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setResumesUrl(String resumesUrl) {
        this.resumesUrl = resumesUrl;
    }

    public UserHH() {
    }

    /**
     * Метод выводит информаицию о пользваотеле
     * @return
     */
    public String toString() {
        String userInfo = new StringBuilder()
                .append("Имя: ")
                .append(getFirstName())
                .append("\n")
                .append("Фамилия: ")
                .append(getLastName())
                .append("\n")
                .append("Телефон: ")
                .append(getPhone())
                .append("\n")
                .append("Почта: ")
                .append(getEmail())
                .toString();
        return userInfo;
    }

}
