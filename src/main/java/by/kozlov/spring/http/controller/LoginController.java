package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.LoginDto;
import by.kozlov.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @GetMapping
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") LoginDto loginDto, RedirectAttributes redirectAttributes) {

        var user = userService.login(loginDto);
        if(user.isPresent()) {
            redirectAttributes.addFlashAttribute("user",user.orElseThrow());
            var id = user.orElseThrow().getId();
            if(user.orElseThrow().getRole().equals(Role.ADMIN)) {
                return "redirect:/users";
            } else {
                return "redirect:/users/" + id;
            }
        }
        return "redirect:/login";
    }

}
