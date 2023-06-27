package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.LoginDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
//@SessionAttributes("user")
public class LoginController {

    private final UserService userService;

    @GetMapping()
    public String loginPage() {

        return "user/login";
    }

    @PostMapping()
    public String login(@ModelAttribute("login") LoginDto loginDto,
                        RedirectAttributes redirectAttributes) {

        Optional<UserReadDto> user = userService.login(loginDto);
        //var loginError = new LoginError("log err","No login or email in database. Please register");
        if(user.isPresent()) {
            redirectAttributes.addFlashAttribute("user", user.get());
            var id = user.get().getId();
            if(user.get().getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/workers";
            } else {
                return "redirect:/worker";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
