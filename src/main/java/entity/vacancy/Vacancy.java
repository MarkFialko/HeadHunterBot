package entity.vacancy;

/**
 * класс вакансии
 */
public class Vacancy {
    private Integer id;
    private String name;
    private Contacts contacts;
    private KeySkills keySkills;


    public Vacancy(Integer id, String name, Contacts contacts, KeySkills keySkills) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
        this.keySkills = keySkills;
    }

    public Integer getId() {
        return id;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public String getName() {
        return name;
    }

    public KeySkills getKeySkills() {
        return keySkills;
    }

    /**
     * получить информацию о вакансии
     *
     * @return строка для вывода в консоль
     */
    public String getInfo() {
        StringBuilder vacancyInfo = new StringBuilder();
        vacancyInfo.append("ID: ")
                .append(this.id)
                .append("\n")
                .append("Название: ")
                .append(this.name)
                .append("\n")
                .append(this.contacts.getContacts())
                .append("\n")
                .append(this.keySkills.getKeySkills());

        return vacancyInfo.toString();
    }
}