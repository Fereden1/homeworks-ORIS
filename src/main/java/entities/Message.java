package entities;

import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {

    private Integer id;
    private String nickname;
    private String text;
    private LocalTime time;

    public Message() {
        this.nickname = nickname;
        this.text = text;
        this.time = time;
    }

    public Message(String nickname, String text, LocalTime time) {
        this.nickname = nickname;
        this.text = text;
        this.time = time;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getFormattedTime() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}