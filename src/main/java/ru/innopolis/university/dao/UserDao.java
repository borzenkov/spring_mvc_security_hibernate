package ru.innopolis.university.dao;

import java.util.List;

import ru.innopolis.university.model.User;


public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}

