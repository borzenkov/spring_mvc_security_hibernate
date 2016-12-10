package ru.innopolis.university.service;

import java.util.List;

import ru.innopolis.university.model.UserProfile;


public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
