package dao;

import entities.Message;

import java.util.List;

public interface messageCRUD {
    void save(Message entity);
    List<Message> findAll();
    List<Message> findById(String id);
    boolean deleteById(String id);
    boolean update(Message entity);
}


