package ru.innopolis.university.dao;

import java.util.List;

import ru.innopolis.university.model.UserProfile;


public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
