package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.List;

/**
 * Класс вакансий
 */
public class Vacancy {
    private static final String API_ADDRESS = "vacancies/";
    private static final ObjectMapper mapper = new ObjectMapper();
    private String name;

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
     * @return
     */
    public String getInfo() {
        return new StringBuilder()
                .append("Вакансия: ")
                .append(this.name)
                .append("\n")
                .append("Сcылка: ")
                .append(this.alternateUrl)
                .append("\n").toString();
    }

    /**
     * Получить список вакансий с API
     * @return
     * @throws JsonProcessingException
     */
    public static List<Vacancy> getVacancies(String query) throws JsonProcessingException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        String responseBody = API.getResponse(API_ADDRESS + query);

        return  mapper.readValue(responseBody, HeadHunter.class).items;
    }

    public static Vacancy getVacancy(String id) throws JsonProcessingException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        String responseBody = API.getResponse(API_ADDRESS + id);

        return  mapper.readValue(responseBody, Vacancy.class);
    }

}
