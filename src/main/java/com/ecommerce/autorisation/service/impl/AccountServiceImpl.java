package com.ecommerce.autorisation.service.impl;

import com.ecommerce.autorisation.dto.UserDto;
import com.ecommerce.autorisation.mappers.UserMapper;
import com.ecommerce.autorisation.repository.AuthorityRepository;
import com.ecommerce.autorisation.repository.UserRepository;
import com.ecommerce.autorisation.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto save(UserDto userDto) {
        if(userDto.getPassword() != null){
            encryptPassword(userDto);
        }

        var user = userMapper.toEntity(userDto);

        if(!user.getAuthorities().isEmpty()){
            user.getAuthorities().forEach(authority -> {
                if(authorityRepository.findByName(authority.getName()).isPresent()){
                    var role = authorityRepository.findByName(authority.getName());
                    user.getAuthorities().remove(authority);
                    user.getAuthorities().add(role.get());
                }
            });
        }
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
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> addUsers(List<UserDto> userDtoList) {
        userDtoList.forEach( userDto -> {
            if(userDto.getPassword() != null){
                encryptPassword(userDto);
            }
        });

        var users = userRepository.saveAll(userDtoList.stream()
                .map(userMapper::toEntity)
                .collect(Collectors.toList()));
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserDto loadUserByEmail(String email) {
        return null;
    }

    private void encryptPassword(UserDto userDto) {
        if(userDto.getPassword() != null){
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }
    }

}
