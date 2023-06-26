package by.kozlov.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workersSets")
@RequiredArgsConstructor
@SessionAttributes("user")
public class WorkersSetsController {

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, Model model,
                         @ModelAttribute("flag") String flag) {
        return "view";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, @ModelAttribute("flag") String flag) {
        return "view";
    }
}
