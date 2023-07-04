package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Gender;
import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.LoginDto;
import by.kozlov.spring.dto.UserCreateEditDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.dto.WorkerCreateEditDto;
import by.kozlov.spring.service.BrigadeService;
import by.kozlov.spring.service.UserService;
import by.kozlov.spring.validation.LoginError;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
//@SessionAttributes("user")
public class LoginController {

    private final UserService userService;
    private final BrigadeService brigadeService;

    @GetMapping()
    public String loginPage(Model model, @ModelAttribute("error") LoginError loginError) {

       if(loginError != null) {
            model.addAttribute("error",loginError);
       }
        return "user/login";
    }

    @PostMapping()
    public String login(Model model,
                        @ModelAttribute("login") LoginDto loginDto,
                        RedirectAttributes redirectAttributes) {

        Optional<UserReadDto> user = userService.login(loginDto);

        if(user.isPresent()) {
            redirectAttributes.addFlashAttribute("user", user.get());
            if(user.get().getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/workers";
            } else {
                return "redirect:/worker";
            }
        } else {
            LoginError loginError = new LoginError("log err","Incorrect login or email. Please retry or register");
            redirectAttributes.addFlashAttribute("error",loginError);
            return "redirect:/login";
        }


    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("userCreate") UserCreateEditDto user) {

        model.addAttribute("userCreate",user);
        model.addAttribute("roles",Role.values());
        model.addAttribute("genders", Gender.values());
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userCreate") UserCreateEditDto user,
                               RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("user",userService.create(user));
        if (user.getRole().equals(Role.ADMIN)){
            return "redirect:/admin/workers";
        } else {
            return "redirect:/worker/create";
        }
    }
}
