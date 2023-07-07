package by.kozlov.spring.http.controller;

import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.service.ProductionService;
import by.kozlov.spring.service.SetService;
import by.kozlov.spring.service.UserService;
import by.kozlov.spring.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@SessionAttributes("user")
public class AdminController {

    private final WorkerService workerService;
    private final ProductionService productionService;
    private final SetService setService;
    private final UserService userService;

    @GetMapping("/workers")
    public String findAll(Model model, @ModelAttribute("user") UserReadDto user) {
        var workers = workerService.findAll();
        model.addAttribute("link",user.getEmail());
        model.addAttribute("workers", workers);
        return "admin/adminStartPage";
    }

    @GetMapping("{id}/worker")
    public String findById(@PathVariable("id") Integer id, Model model) {

        Sort sort = Sort.by("dateOfProduction");
        var production = productionService.findAllByWorkerId(id,sort);
        var worker = workerService.findById(id).orElseThrow();
        model.addAttribute("production", production);
        model.addAttribute("worker",worker);
        return "admin/adminWorker";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("user")UserReadDto user) {
        if(!userService.delete(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/login";
    }
}
