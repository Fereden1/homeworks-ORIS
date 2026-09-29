package dao;

import entities.User;

public interface userCRUD {

    void save(User user);
    User findByLogin(String login);
    User findByConfirmationCode(String code);
    void confirmUser(int id, long telegramChatId);
    boolean existsByLogin(String login);
    boolean existsByTelegramChatId(long telegramChatId);

}
