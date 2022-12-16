package database.user;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Users {
    @Id
    @Column(name = "telegram_id", nullable = true)
    private Integer telegramId;
    @Basic
    @Column(name = "access_token", nullable = true, length = -1)
    private String accessToken;

    public Integer getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Integer telegramId) {
        this.telegramId = telegramId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(telegramId, users.telegramId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telegramId);
    }
}
