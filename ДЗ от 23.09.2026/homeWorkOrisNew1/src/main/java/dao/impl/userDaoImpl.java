package dao.impl;

import dao.userCRUD;
import entities.User;
import utils.DBConnection;

import java.sql.*;

public class userDaoImpl implements userCRUD {

    private static final userDaoImpl instance = new userDaoImpl();

    private final Connection connection = DBConnection.getConnection();

    private userDaoImpl() {
    }

    public static userDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void save(User user) {
        String sql = " INSERT INTO users (login, password, confirmation_status, confirmation_code, telegram_chat_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getConfirmationStatus());
            stmt.setString(4, user.getConfirmationCode());
            stmt.setNull(5, Types.BIGINT);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return createUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User findByConfirmationCode(String code) {
        String sql = "SELECT * FROM users WHERE confirmation_code = ? AND confirmation_status = 'pending'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return createUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void confirmUser(int id, long telegramChatId) {
        String sql = "UPDATE users SET confirmation_status = 'confirmed', telegram_chat_id = ?, confirmation_code = NULL WHERE id = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, telegramChatId);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByLogin(String login) {
        String sql = "SELECT id FROM users WHERE login = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByTelegramChatId(long telegramChatId) {
        String sql = "SELECT id FROM users WHERE telegram_chat_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, telegramChatId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setConfirmationStatus(rs.getString("confirmation_status"));
        user.setConfirmationCode(rs.getString("confirmation_code"));

        long chatId = rs.getLong("telegram_chat_id");
        if (!rs.wasNull()) {
            user.setTelegramChatId(chatId);
        }
        return user;
    }
}
