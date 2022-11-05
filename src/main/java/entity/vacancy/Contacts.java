package entity.vacancy;

import entity.user.ContactsData;

/**
 * класс контактных данных вакансии
 */
public class Contacts {
    private String phone;
    private String email;

    public Contacts(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    /**
     *  получает контактные данные в вакансии
     * @return строка для вывода в консоль
     */
    public String getContacts() {
        StringBuilder contacts = new StringBuilder();
        contacts.append(ContactsData.PHONE.value())
                .append(phone)
                .append("\n")
                .append(ContactsData.EMAIL.value())
                .append(email);

        return contacts.toString();
    }
}