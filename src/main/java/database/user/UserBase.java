package database.user;

import database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Класс для работы с запросами к таблице пользователей
 */
public class UserBase {
    /**
     * Метод проверяет, зарегестрирован ли пользователь или нет
     * @param telegramId идентификатор пользователя в телеграм
     * @return
     */
    public Users findByTelegramId(Integer telegramId) {
        Transaction transaction = null;
        Users user;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Users> query = session.createQuery("SELECT b FROM database.user.Users b WHERE b.telegramId = :id", Users.class);
            query.setParameter("id", telegramId);
            user = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
        return user;
    }

    /**
     * Метод сохраняет пользователя в таблицу пользователей
     * @param newUser
     * @return
     */
    public Users save(Users newUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
        return newUser;
    }

    /**
     * Метод обновляет информацию о пользователе в таблице
     * @param oldUser пользователеь с обновленными полями
     * @return
     */
    public Users update(Users oldUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(oldUser);
            transaction.commit();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
        return oldUser;
    }

}
