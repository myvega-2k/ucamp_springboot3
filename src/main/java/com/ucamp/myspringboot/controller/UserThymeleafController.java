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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserThymeleafController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    //삭제 처리(DB에 delete)하기
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/index";
    }

    //수정 처리(DB에 update)하기
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @Valid @ModelAttribute("user") UserReqFormDTO formDTO,
                             BindingResult result) {
        //입력항목 에러가 발생하면 update-user.html Form을 계속 띄워주기
        if (result.hasErrors()) {
            return "update-user";
        }
        //Service 수정 메서드 호출
        userService.updateUserForm(formDTO);

//        model.addAttribute("users", userService.getUserList());
//        return "index";
        return "redirect:/index";
    }

    //수정 Form 띄워주기
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        UserResDTO userResDTO = userService.getUser(id);
        model.addAttribute("user",userResDTO);
        return "update-user";
    }

    //등록 처리(DB에 insert)하기
    @PostMapping("/adduser")
    public String addUser(@Valid @ModelAttribute("user") UserReqDTO userForm,
                          BindingResult result) {
        //입력항목 에러가 발생하면 add-user.html Form을 계속 띄워주기
        if (result.hasErrors()) {
            return "add-user";
        }
        //Service 등록 메서드 호출
        userService.addUser(userForm);

//        model.addAttribute("users", userService.getUserList());
//        return "index";
        return "redirect:/index";
    }

    //등록 Form 띄워주기
    @GetMapping("/signup")
    public String showSignUpForm(@ModelAttribute("user") UserReqDTO reqDTO) {
        //model.addAttribute("user",reqDTO);
        return "add-user";
    }

    //사용자 목록 가져오기
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
