package entity;

import api.API;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.List;

public class Vacancy {

    static class Schedule {
        public String id;
        public String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


    private static final String API_ADDRESS = "vacancies/";
    private static final ObjectMapper mapper = new ObjectMapper();
    private String name;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private Schedule schedule;

    private Salary salary;

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    private String alternateUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlternateUrl(String alternateUrl) {
        this.alternateUrl = alternateUrl;
    }

    public String getAlternateUrl() {
        return alternateUrl;
    }

    /**
     * Получить информацию о вакансии в виде строки, которую выведет телеграм бот
     *
     * @return
     */
    public String toString() {
        StringBuilder vacancyData = new StringBuilder()
                .append(VacancyData.VACANCY_NAME)
                .append(name)
                .append("\n")
                .append(VacancyData.VACANCY_SCHEDULE)
                .append(schedule.getName())
                .append("\n");

        if (salary != null) {
            vacancyData.append(VacancyData.VACANCY_SALARY)
                    .append(VacancyData.VACANCY_SALARY_FROM);
            String salaryFrom = salary.from == null ? "0" : salary.from.toString();
            String salaryTo = salary.to == null ? "*Не указано*" : salary.to.toString();
            vacancyData.append(salaryFrom)
                    .append(VacancyData.VACANCY_SALARY_TO)
                    .append(salaryTo);

        }

        vacancyData.append("\n")
                .append(VacancyData.VACANCY_LINK)
                .append(alternateUrl);

        return vacancyData.toString();
    }

    /**
     * Получить список вакансий с API
     *
     * @return
     * @throws JsonProcessingException
     */
    public static List<Vacancy> getVacancies(String query) throws JsonProcessingException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        String responseBody = API.getResponse(API_ADDRESS + query);

        return mapper.readValue(responseBody, HeadHunter.class).items;
    }

    public static Vacancy getVacancy(String id) throws JsonProcessingException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        String responseBody = API.getResponse(API_ADDRESS + id);

        return mapper.readValue(responseBody, Vacancy.class);
    }

}
