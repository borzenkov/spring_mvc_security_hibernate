package ru.innopolis.university.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.innopolis.university.model.Duck;

import java.util.List;

@Repository("duckDao")
public class DuckDaoImpl extends AbstractDao<Integer, Duck> implements DuckDao {
    @Override
    public Duck findById(int id) {
        Duck duck = getByKey(id);
        if(duck!=null){
            Hibernate.initialize(duck.getUserProfiles());
        }
        return user;
    }

    @Override
    public Duck findBySSO(String sso) {
        return null;
    }

    @Override
    public void save(Duck duck) {

    }

    @Override
    public void deleteBySSO(String sso) {

    }

    @Override
    public List<Duck> findAllDucks() {
        return null;
    }
}
