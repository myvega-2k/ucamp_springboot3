package com.ucamp.myspringboot.controller;

import com.ucamp.myspringboot.dto.UserResDTO;
import com.ucamp.myspringboot.entity.User;
import com.ucamp.myspringboot.exception.BusinessException;
import com.ucamp.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private final UserRepository userRepository;

    //Constructor Injection
//    public UserRestController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        //Optional<User> optionalUser = userRepository.findById(id);
        return getUserNotFound(id);

    }

    private User getUserNotFound(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    

    @PatchMapping("/{email}/")
    public User updateUser(@PathVariable String email, @RequestBody User userDetail) {
        User existUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));

        existUser.setName(userDetail.getName());
        return userRepository.save(existUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User existUser = getUserNotFound(id);
        userRepository.delete(existUser);
        return  ResponseEntity.ok("Id = " + id + " User가 삭제 되었습니다.");
    }

}
