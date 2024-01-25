package com.ecommerce.autorisation.service.impl;

import com.ecommerce.autorisation.dto.UserDto;
import com.ecommerce.autorisation.mappers.UserMapper;
import com.ecommerce.autorisation.models.User;
import com.ecommerce.autorisation.repository.UserRepository;
import com.ecommerce.autorisation.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto save(UserDto userDto) {
        var user = userMapper.toEntity(userDto);
        try {
            log.debug("Enregistrement d'un User : {}", userDto);
            userRepository.save(user);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> addUsers(List<UserDto> userDtoList) {
        /*userDtoList.forEach( userDto -> {
            if(userDto.getPassword() != null){
                encryptPassword(userDto);
            }
        });*/

        var users = userRepository.saveAll(userDtoList.stream()
                .map(userMapper::toEntity)
                .collect(Collectors.toList()));
        var usersDto = users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return  usersDto;

    }

    /*private void encryptPassword(UserDto userDto) {
        if(userDto.getPassword() != null){
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }
    }*/

}
