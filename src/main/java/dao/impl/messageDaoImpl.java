package dao.impl;

import dao.messageCRUD;
import entities.Message;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class messageDaoImpl implements messageCRUD {

    private final Connection connection = DBConnection.getConnection();

    @Override
    public void save(Message entity) {
        String sql = "INSERT INTO messages (nickname, text, time) VALUES (?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql))
        {
            stm.setString(1,(entity.getNickname()));
            stm.setString(2,(entity.getText()));
            stm.setTime(3, Time.valueOf(entity.getTime()));
            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();

        String sql = "SELECT nickname, text, time FROM messages";

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Message message = new Message(
                        rs.getString("nickname"),
                        rs.getString("text"),
                        rs.getTime("time").toLocalTime()
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public List<Message> findById(String id) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message app = new Message();
                app.setNickname(rs.getString("nickname"));
                app.setText(rs.getString("text"));
                app.setTime(rs.getTime("time").toLocalTime());
                messages.add(app);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }


    @Override
    public boolean deleteById(String id) {
        String sql = "DELETE FROM messages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Message entity) {
        String sql = "UPDATE messages SET nickname = ?, text = ?, time = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getNickname());
            stmt.setString(2, entity.getText());
            stmt.setTime(3, Time.valueOf(entity.getTime()));
            stmt.setInt(4, entity.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
