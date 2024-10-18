package com.ucamp.myspringboot.service;

import com.ucamp.myspringboot.dto.UserReqDTO;
import com.ucamp.myspringboot.dto.UserResDTO;
import com.ucamp.myspringboot.entity.User;
import com.ucamp.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResDTO addUser(UserReqDTO reqDTO){
        //DTO => Entity
        User userEntity = modelMapper.map(reqDTO, User.class);
        User saved = userRepository.save(userEntity);
        //Entity => DTO
        return modelMapper.map(saved, UserResDTO.class);
    }



}
