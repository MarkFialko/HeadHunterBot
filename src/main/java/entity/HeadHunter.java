package entity;

import java.util.List;

/**
 * Класс для корректной обработки Jackson`ом
 */
class HeadHunter {
    List<Vacancy> items;

    public List<Vacancy> getItems() {
        return items;
    }

    public void setItems(List<Vacancy> vacancies) {
        this.items = vacancies;
    }

    HeadHunter() {
    }
}
