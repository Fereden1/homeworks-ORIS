package dao.impl;

import dao.messageCRUD;
import entities.Message;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class messageDaoImpl implements messageCRUD {

    private static final messageDaoImpl instance = new messageDaoImpl();

    private final Connection connection = DBConnection.getConnection();

    private messageDaoImpl() {
    }

    public static messageDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void save(Message message) {

        String sql = "INSERT INTO messages (user_id, text) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, message.getUserId());
            stmt.setString(2, message.getText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> findAll() {

        List<Message> messages = new ArrayList<>();
        String sql = "SELECT messages.id, messages.user_id, users.login, messages.text, messages.created_at FROM messages JOIN users ON messages.user_id = users.id ORDER BY messages.id ";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Message message = new Message(
                        rs.getInt("user_id"),
                        rs.getString("login"),
                        rs.getString("text"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                message.setId(rs.getInt("id"));
                messages.add(message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }

    @Override
    public Message findById(int id) {

        String sql = "SELECT messages.id, messages.user_id, users.login, messages.text, messages.created_at FROM messages JOIN users ON messages.user_id = users.id WHERE messages.id = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Message message = new Message(
                        rs.getInt("user_id"),
                        rs.getString("login"),
                        rs.getString("text"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                message.setId(rs.getInt("id"));
                return message;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {

        String sql = "DELETE FROM messages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Message message) {
        String sql = "UPDATE messages SET text = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, message.getText());
            stmt.setInt(2, message.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}