package ru.innopolis.university.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.university.model.Duck;

/**
 * Created by imac on 12.12.16.
 */
@Service("duckService")
@Transactional
public class DuckServiceImpl implements DuckService{
    @Override
    public void saveDuck(Duck duck) {
        System.out.println("save duck");
    }
}
