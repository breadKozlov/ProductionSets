package by.kozlov.spring.http.controller;

import by.kozlov.spring.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workers")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/admin")
    public String findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
        //var user = redirectAttributes.getFlashAttributes().get("user");
        var workers = workerService.findAll();
        model.addAttribute("workers", workers);
        //model.addAttribute("user", user);
        return "worker/admin";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("users", userService.findAll());
        //var user = redirectAttributes.getFlashAttributes().get("user");
        var workers = workerService.findAll();
        model.addAttribute("workers", workers);
        //model.addAttribute("user", user);
        return "worker/admin";
    }
}
