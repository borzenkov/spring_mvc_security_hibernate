package ru.innopolis.university.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ru.innopolis.university.dto.UserDto;
import ru.innopolis.university.model.User;

public class UserMapper extends ConfigurableMapper {
    public void configure(MapperFactory factory) {
        factory.classMap(User.class, UserDto.class)
                .field("id", "id")
                .field("ssoId", "ssoId")
                .field("password", "password")
                .field("firstName", "firstName")
                .field("lastName", "lastName")
                .field("email", "email")
                .field("userProfiles", "userProfiles")
                .byDefault()
                .register();
    }
}