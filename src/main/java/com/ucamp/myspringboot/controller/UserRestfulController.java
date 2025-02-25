package com.ucamp.myspringboot.controller;

import com.ucamp.myspringboot.dto.UserReqDTO;
import com.ucamp.myspringboot.dto.UserResDTO;
import com.ucamp.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestfulController {
    private final UserService userService;

    @PostMapping
    public UserResDTO addUser(@RequestBody UserReqDTO reqDTO){
        return userService.addUser(reqDTO);
    }

    @GetMapping("/{id}")
    public UserResDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserResDTO> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/{email}/")
    public UserResDTO getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    public UserResDTO updateUser(@PathVariable Long id,
                                 @RequestBody UserReqDTO reqDTO){
        return userService.updateUser(id,reqDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return  ResponseEntity.ok("Id = " + id + " User가 삭제 되었습니다.");
    }
}
