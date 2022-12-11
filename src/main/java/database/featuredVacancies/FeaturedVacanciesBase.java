package database.featuredVacancies;

import database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

/**
 * Класс для работы с запросами к таблице избранных вакансий
 */
public class FeaturedVacanciesBase {
    /**
     * Метод проверяет, добавлена ли та же самая вакансия у конкретного пользователя
     * @param vacancyId идентификатор вакансии
     * @param userId идентификатор пользователя в телеграмме
     * @return
     */
    public boolean find(String vacancyId, Integer userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("SELECT * FROM featured_vacancies WHERE user_id = :id AND vacancy_id = :vacId");
            query.setParameter("id", userId);
            query.setParameter("vacId", vacancyId);

            List<Object[]> result = query.getResultList();
            transaction.commit();

            if (result.isEmpty()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return false;
        }
    }

    /**
     * Метод для удаления из таблицы вакансий вакансии у конкретного пользваотеля
     * @param userId идентификатор пользователя в телеграмме
     * @param vacancyId идентификатор вакансии
     * @return Булеове значение, удалилась ли вакансия успешно
     */
    public boolean delete(Integer userId, String vacancyId) {
        Transaction transaction = null;
        List<FeaturedVacancies> vacancies;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("DELETE FROM featured_vacancies WHERE user_id = :id and vacancy_id = :vacId");
            query.setParameter("id", userId);
            query.setParameter("vacId", vacancyId);

            int result = query.executeUpdate();

            transaction.commit();

            if (result == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return false;
        }
    }

    /**
     * Метод для получения всех избранных вакансий у конкретного пользователя
     * @param userId идентификаотр пользователя
     * @return Список со всеми вакансиями, которые находятся в избранном
     */
    public List<FeaturedVacancies> show(Integer userId) {
        List<FeaturedVacancies> result;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<FeaturedVacancies> query = session.createQuery(" SELECT b FROM database.featuredVacancies.FeaturedVacancies b WHERE b.userId = :id", FeaturedVacancies.class);
            query.setParameter("id", userId);
            result = query.getResultList();
            transaction.commit();
            return result;
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
    }

    /**
     * Метод добавляет вакансиб в избранное
     * @param newFeaturedVacancy  экзкмепляр избранной вакансии
     * @return успешно ли добавлена вакансия
     */
    public boolean save(FeaturedVacancies newFeaturedVacancy) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(newFeaturedVacancy);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return false;
        }
    }

}




