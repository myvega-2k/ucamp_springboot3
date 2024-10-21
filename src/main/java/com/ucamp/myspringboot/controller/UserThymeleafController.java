package com.ucamp.myspringboot.controller;

import com.ucamp.myspringboot.dto.UserReqDTO;
import com.ucamp.myspringboot.dto.UserResDTO;
import com.ucamp.myspringboot.dto.form.UserReqFormDTO;
import com.ucamp.myspringboot.entity.User;
import com.ucamp.myspringboot.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserThymeleafController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/adduser")
    public String addUser(@Valid @ModelAttribute("user") UserReqDTO userForm, BindingResult result, Model model) {
        //입력항목 에러가 발생하면 add-user.html Form을 계속 띄워주기
        if (result.hasErrors()) {
            return "add-user";
        }
        //Service 등록 메서드 호출
        userService.addUser(userForm);
        model.addAttribute("users", userService.getUserList());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(@ModelAttribute("user") UserReqDTO reqDTO, Model model) {
        //model.addAttribute("user",reqDTO);
        return "add-user";
    }


    @GetMapping("/index")
    public ModelAndView index() {
        List<UserResDTO> userList = userService.getUserList();
        return new ModelAndView("index","users",userList);
    }

    @GetMapping("/thymeleaf")
    public String leaf(Model model) {
        model.addAttribute("name","스프링부트");
        return "leaf";
    }
}
