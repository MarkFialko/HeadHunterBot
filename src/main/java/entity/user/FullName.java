package entity.user;

/**
 * класс полного имени пользователя
 */
public class FullName {
    private String firstName;
    private String lastName;
    private String middleName;

    public FullName(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        fullName
                .append(FullNameData.INFO.value())
                .append(firstName)
                .append(" ")
                .append(lastName)
                .append(" ")
                .append(middleName)
                .append(" ");

        return fullName.toString();
    }
}
