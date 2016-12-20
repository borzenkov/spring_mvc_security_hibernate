package ru.innopolis.university.dao;

import ru.innopolis.university.model.Duck;

import java.util.List;

public interface DuckDao {

    Duck findById(int id);

    Duck findBySSO(String sso);

    void save(Duck duck);

    void deleteBySSO(String sso);

    List<Duck> findAllDucks();

}