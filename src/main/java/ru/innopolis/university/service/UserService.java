package ru.innopolis.university.service;

import java.util.List;

import ru.innopolis.university.dto.UserDto;
import ru.innopolis.university.model.User;


public interface UserService {

    UserDto findById(int id);

    //User findBySSO(String sso);
    UserDto findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<UserDto> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

}