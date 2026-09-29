package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {

    private Integer id;
    private Integer userId;
    private String login;
    private String text;
    private LocalDateTime createdAt;

    public Message() {
    }

    public Message(Integer userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    public Message(Integer userId, String login, String text, LocalDateTime createdAt) {
        this.userId = userId;
        this.login = login;
        this.text = text;
        this.createdAt = createdAt;
    }

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getFormattedTime() {
        return createdAt.format(
                DateTimeFormatter.ofPattern("HH:mm")
        );
    }
}