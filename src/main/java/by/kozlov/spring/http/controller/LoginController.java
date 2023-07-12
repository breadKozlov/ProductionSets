package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Gender;
import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.LoginDto;
import by.kozlov.spring.dto.UserCreateEditDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.service.BrigadeService;
import by.kozlov.spring.service.UserService;
import by.kozlov.spring.service.WorkerService;
import by.kozlov.spring.validation.LoginError;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final WorkerService workerService;

    @GetMapping()
    public String loginPage(Model model, @ModelAttribute("err") LoginError loginError) {

       if(loginError != null) {
            model.addAttribute("err",loginError);
       }
        return "user/login";
    }

    @PostMapping()
    public String login(@ModelAttribute("login") LoginDto loginDto, HttpSession session,
                        RedirectAttributes redirectAttributes) {

        Optional<UserReadDto> user = userService.login(loginDto);


        if(user.isPresent()) {
            session.setAttribute("user",user.get());
            redirectAttributes.addFlashAttribute("user", user.get());
            if(user.get().getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/workers";
            } else {
                return "redirect:/worker";
            }
        } else {
            LoginError loginError = new LoginError("log err","Incorrect login or email." +
                    " Please retry or register");
            redirectAttributes.addFlashAttribute("err",loginError);
            return "redirect:/login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration(Model model,
                               @ModelAttribute("userCreate") UserCreateEditDto user) {

        model.addAttribute("userCreate",user);
        model.addAttribute("roles",Role.values());
        model.addAttribute("genders", Gender.values());
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userCreate") @Validated UserCreateEditDto user,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {

        if(bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors();
            var errorAge = errors.stream().filter(it -> Objects.equals(it.getDefaultMessage(), "Your age less than 18")).findFirst();
            if(errorAge.isPresent()) {
                redirectAttributes.addFlashAttribute("error",errorAge.orElseThrow());
                return "redirect:/login/attentionAge";
            }
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:/login/registration";
        }
        if(userService.checkEmail(user.getEmail())) {
            return "redirect:/login/crashEmail";
        }
        var userRead = userService.create(user);
        redirectAttributes.addFlashAttribute("user",userRead);
        session.setAttribute("user",userRead);
        if(workerService.findByEmail(user.getEmail()).isPresent()) {
            return "redirect:/worker";
        }
        if (user.getRole().equals(Role.ADMIN)){
            return "redirect:/admin/workers";
        } else {
            return "redirect:/worker/create";
        }
    }

    @GetMapping("/crashEmail")
    public String crashEmail() {
        return "exceptions/crashEmail";
    }

    @GetMapping("/attentionAge")
    public String attentionAge() {
        return "exceptions/attentionAge";
    }
}
