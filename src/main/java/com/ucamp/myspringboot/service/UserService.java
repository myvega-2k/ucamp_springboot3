package com.ucamp.myspringboot.service;

import com.ucamp.myspringboot.dto.UserReqDTO;
import com.ucamp.myspringboot.dto.UserResDTO;
import com.ucamp.myspringboot.entity.User;
import com.ucamp.myspringboot.exception.BusinessException;
import com.ucamp.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public UserResDTO getUser(Long id) {
//        User entity = userRepository.findById(id)
//                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
//        return modelMapper.map(entity, UserResDTO.class);

        return userRepository.findById(id) //Optional<User>
                //map(Function) Function 의 추상메서드 R apply(T t)
                .map(entity -> modelMapper.map(entity,UserResDTO.class))
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
    }

    //List<User> => List<UserResDTO>
    //List<User> -> Stream<User> -> Stream<UserResDtO> -> List<UserResDTO>
    public List<UserResDTO> getUserList(){
        List<User> userEntityList = userRepository.findAll();
        return userEntityList.stream() //Stream<User>
                .map(entity -> modelMapper.map(entity,UserResDTO.class)) //Stream<UserResDTO>
                .toList(); //List<UserResDTO>
    }





}
