package entities;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {

    private String nickname;
    private String text;
    private LocalTime time;

    public Message(String nickname, String text, LocalTime time) {
        this.nickname = nickname;
        this.text = text;
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public String getText() {
        return text;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getFormattedTime() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}