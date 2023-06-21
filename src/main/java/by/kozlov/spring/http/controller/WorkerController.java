package by.kozlov.spring.http.controller;

import by.kozlov.spring.dto.UserDto;
import by.kozlov.spring.service.ProductionService;
import by.kozlov.spring.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.PredicateEvalInfo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/workers")
@RequiredArgsConstructor
@SessionAttributes("user")
public class WorkerController {

    private final WorkerService workerService;
    private final ProductionService productionService;

    @GetMapping("/admin")
    public String findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
        var workers = workerService.findAll();
        model.addAttribute("workers", workers);
        return "worker/admin";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {

        Sort sort = Sort.by("dateOfProduction");
        var production = productionService.findAllByWorkerId(id,sort);
        model.addAttribute("kits", production);
        //model.addAttribute("user", user);
        return "worker/adminWorker";
    }
}
