package entity.commands;

import entity.plugs.Plugs;
import entity.vacancy.Vacancy;

import java.util.List;

/**
 * класс команды для вакансий
 */
public class VacanciesCommand implements ICommand {
    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();

        info.append("Команда /vacancies выводит информацию\n")
                .append("   обо всех достуаных вакансиях\n");

        return info.toString();
    }

    /**
     * выводит в консоль список всех вакансий
     *
     * @param args аргументы из командной строки
     */
    @Override
    public void execute(String[] args) {
        List<Vacancy> vacancies = Plugs.getVacancies();
        for (Vacancy vacancy : vacancies) {
            System.out.println(vacancy.getInfo());
        }
    }

    public String getName() {
        return "/vacancies";
    }
}
