package entity.filter;

import entity.plugs.Plugs;
import entity.vacancy.Vacancy;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс фильтрации
 */
public class Filter {
    /**
     * собирает все фильтры
     *
     * @param filterQuery строка с фиьлтрами
     * @return возвращает хешмап, где ключ - имя фильтра, значение - критерий фильтрации
     */
    public HashMap<String, String> getFilterQueries(String filterQuery) {
        HashMap<String, String> filterQueries = new HashMap<>();

        String[] filterList = filterQuery.split("&");

        for (String filter : filterList) {
            String key = filter.substring(0, filter.indexOf("="));
            String value = filter.substring(filter.indexOf("=") + 1);

            filterQueries.put(key, value);

        }

        return filterQueries;
    }

    /**
     * @param vacancy вакансия
     * @param filters хэшмап фильтров
     * @return булевое значение, подошла вакансия или нет
     */
    private boolean checkValidVacancy(Vacancy vacancy, HashMap<String, String> filters) {
        Collection<String> filterKeys = filters.keySet();
        for (String key : filterKeys) {
            String value = filters.get(key);
            switch (key) {
                case "name":
                    if (!Objects.equals(vacancy.getName(), value)) return false;
                    break;
                case "id":
                    if (!Objects.equals(vacancy.getId().toString(), value)) return false;
                    break;
                case "keySkill":
                    if (!vacancy.getKeySkills().getKeySkills().contains(value)) return false;
                    break;
            }
        }
        return true;
    }

    /**
     * @param filterQuery
     * @return список отфильтрованных вакансий
     */
    public List<Vacancy> filterVacancy(String filterQuery) {
        HashMap<String, String> filtersMap = this.getFilterQueries(filterQuery);
        List<Vacancy> vacancies = Plugs.getVacancies();

        return vacancies.stream().filter(vacancy -> checkValidVacancy(vacancy, filtersMap)).collect(Collectors.toList());
    }


}
