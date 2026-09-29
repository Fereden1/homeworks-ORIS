package dao;

import entities.Message;

import java.util.List;

public interface messageCRUD {

    void save(Message message);
    List<Message> findAll();
    Message findById(int id);
    boolean deleteById(int id);
    boolean update(Message message);

}