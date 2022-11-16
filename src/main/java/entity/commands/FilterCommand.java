package entity.commands;

import entity.filter.Filter;
import entity.vacancy.Vacancy;

import java.util.List;

/**
 * класс команды для фильтрации
 */
public class FilterCommand implements ICommand {
    /**
     * @return строка с информацией о команде
     */
    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();

        info.append("Команда /filter фильтрует вакансии\n")
                .append("   доступные фильтры: name, id, keySkill\n")
                .append("   пример фильтра: name=Программист&keySkill=Открытость");

        return info.toString();
    }

    /**
     * @param args аргументы из командной строки
     *             выводит в консоль список вакансий, которые удовлетворяют фильтру
     */
    @Override
    public void execute(String[] args) {
        Filter filter = new Filter();

        List<Vacancy> filterVacancies = filter.filterVacancy(args[1]);
        if (filterVacancies.size() == 0) {
            System.out.println("Вакансий с таким фильтром нет\n");
            return;
        }
        System.out.println(
                new StringBuilder("Найдено ").
                        append(filterVacancies.size())
                        .append(" вакансий").toString()
        );
        for (Vacancy vacancy : filterVacancies) {
            System.out.println(vacancy.getInfo());
        }

    }

    @Override
    public String getName() {
        return "/filter";
    }
}
