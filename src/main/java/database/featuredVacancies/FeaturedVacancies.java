package database.featuredVacancies;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "featured_vacancies", schema = "public", catalog = "headhunterbot")
public class FeaturedVacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "vacancy_id", nullable = true, length = -1)
    private String vacancyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeaturedVacancies that = (FeaturedVacancies) o;
        return Objects.equals(userId, that.userId) && Objects.equals(vacancyId, that.vacancyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, vacancyId);
    }
}
