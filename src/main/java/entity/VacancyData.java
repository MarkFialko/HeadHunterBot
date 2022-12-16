package entity;

public enum VacancyData {
    VACANCY_NAME("Название вакансии: "),
    VACANCY_SCHEDULE("График работы: "),
    VACANCY_SALARY("Заработная плата: "),
    VACANCY_SALARY_FROM("от "),
    VACANCY_SALARY_TO(" до "),
    VACANCY_LINK("Ссылка: ");


    private String value;

    VacancyData(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
