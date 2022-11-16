package entity.plugs;


import entity.user.FullName;
import entity.user.User;
import entity.vacancy.Contacts;
import entity.vacancy.KeySkills;
import entity.vacancy.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * класс заглушек
 */
public class Plugs {
    /**
     *
     * @return возвращает список зашлушек вакансий
     */
    public static List<Vacancy> getVacancies() {
        List<Vacancy> vacancies = new ArrayList<>();
        Vacancy developer = new Vacancy(
                1,
                "Программист",
                new Contacts("+7 (912) 202-64-40", "bestwork@mail.ru"),
                new KeySkills("Коммуникабельность", "Открытость", "Стрессоустойчивость")
        );
        Vacancy teacher = new Vacancy(
                2,
                "Учитель",
                new Contacts("+7 (922) 000-76-40", "teacher@mail.ru"),
                new KeySkills("Усидчивость", "Опытность")
        );
        Vacancy admin = new Vacancy(
                3,
                "Администроатор",
                new Contacts("+7 (912) 294-23-90", "admin@mail.ru"),
                new KeySkills("Ничего не надо")
        );
        Vacancy teacherTwo = new Vacancy(
                4,
                "Учитель",
                new Contacts("+7 (901) 823-02-23", "teacher2@mail.ru"),
                new KeySkills("Ничего не надо")
        );
        vacancies.add(developer);
        vacancies.add(teacher);
        vacancies.add(teacherTwo);
        vacancies.add(admin);

        return vacancies;
    }
    /**
     *
     * @return возвращает список зашлушек пользователей
     */
    public static List<User> getUsers() {
        List<User> users = new ArrayList<User>(10);

        User userOne = new User(
                new FullName("Марк", "Фиалко", "Григорьевич"),
                new entity.user.Contacts("+7 (912) 208-92-24", "markfialko@gmail.com"),
                1);
        User userTwo = new User(
                new FullName("Иван", "Иванов", "Сергеевич"),
                new entity.user.Contacts("+7 (922) 163-04-11", "ivanov@gmail.com"),
                2);
        User userThree = new User(
                new FullName("Петя", "Альваров", "Иванович"),
                new entity.user.Contacts("+7 (902) 165-56-23", "alvarov@gmail.com"),
                3);

        users.add(userOne);
        users.add(userTwo);
        users.add(userThree);

        return users;
    }
}
