package ru.innopolis.university.service;

import java.util.ArrayList;
import java.util.List;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.innopolis.university.dao.UserDao;
import ru.innopolis.university.dto.UserDto;
import ru.innopolis.university.mapper.UserMapper;
import ru.innopolis.university.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto findById(int id) {
        User user = dao.findById(id);

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        UserMapper userMapper = new UserMapper();
        userMapper.configure(mapperFactory);

        UserDto userDto = userMapper.map(user, UserDto.class);
        return userDto;
    }

    /*public User findBySSO(String sso) {
        User user = dao.findBySSO(sso);
        return user;
    }*/
    public UserDto findBySSO(String sso) {
        User user = dao.findBySSO(sso);

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        UserMapper userMapper = new UserMapper();
        userMapper.configure(mapperFactory);

        UserDto userDto = userMapper.map(user, UserDto.class);
        return userDto;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setSsoId(user.getSsoId());
            if(!user.getPassword().equals(entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
        }
    }


    public void deleteUserBySSO(String sso) {
        dao.deleteBySSO(sso);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = dao.findAllUsers();

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        UserMapper userMapper = new UserMapper();
        userMapper.configure(mapperFactory);

        List<UserDto> userDtos = new ArrayList<UserDto>();
        for(User user : users) {
            UserDto userDto = userMapper.map(user, UserDto.class);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    /*public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }*/
    public boolean isUserSSOUnique(Integer id, String sso) {
        UserDto userDto = findBySSO(sso);
        return ( userDto == null || ((id != null) && (userDto.getId() == id)));
    }

}
