package ru.innopolis.university.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.innopolis.university.dto.UserDto;
import ru.innopolis.university.model.User;
import ru.innopolis.university.model.UserProfile;
import ru.innopolis.university.service.UserService;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        UserDto userDto = userService.findBySSO(ssoId);
        logger.info("User : {}", userDto);
        if(userDto==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(userDto.getSsoId(), userDto.getPassword(),
                true, true, true, true, getGrantedAuthorities(userDto));
    }


    private List<GrantedAuthority> getGrantedAuthorities(UserDto userDto){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(UserProfile userProfile : userDto.getUserProfiles()){
            logger.info("UserProfile : {}", userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }

}
