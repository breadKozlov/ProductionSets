package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.dto.WorkerCreateEditDto;
import by.kozlov.spring.service.*;
import by.kozlov.spring.validation.LoginError;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
@RequestMapping("/worker")
@RequiredArgsConstructor
@SessionAttributes("user")
public class WorkerController {

    private final WorkerService workerService;
    private final WorkersSetsService workersSetsService;
    private final ProductionService productionService;
    private final DifferenceService differenceService;
    private final BrigadeService brigadeService;
    private final UserService userService;

    @GetMapping()
    public String findWorker(Model model, @ModelAttribute("user")UserReadDto user) {

        Sort sort = Sort.by("dateOfProduction");
        try {
            var worker = workerService.findByEmail(user.getEmail()).orElseThrow();
            var setsOfWorker = workersSetsService.findAllByWorkerId(worker.getId());
            var releasedSets = productionService.findAllByWorkerId(worker.getId(),sort);
            model.addAttribute("worker",worker);
            model.addAttribute("setsOfWorker",setsOfWorker);
            model.addAttribute("releasedSets",releasedSets);
            model.addAttribute("diffSets",differenceService.findAllDifferenceWorkerSets(worker.getId()));
        } catch (RuntimeException exception) {
            LoginError loginError = new LoginError("log err","Your account is not in the database." +
                    " Please delete this account and register again");
            model.addAttribute("err",loginError);
            return "exceptions/empty";
        }
        return "worker/workerStartPage";
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("user") UserReadDto user,
                         @ModelAttribute WorkerCreateEditDto worker) {

        var specialities = Arrays.asList("extruder operator","extruder foreman");
        var brigades = brigadeService.findAll();
        model.addAttribute("email",user.getEmail());
        model.addAttribute("worker",worker);
        model.addAttribute("specialities",specialities);
        model.addAttribute("brigades",brigades);
        return "user/additionalInfoWorker";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("worker") @Validated WorkerCreateEditDto worker,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("worker",worker);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/worker/create";
        }
        redirectAttributes.addFlashAttribute(workerService.create(worker));
        return "redirect:/worker";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id,
                         @ModelAttribute("user") UserReadDto user, HttpSession session) {

        session.invalidate();
        if(!workerService.delete(id) || !userService.delete(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/login";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("user")UserReadDto user, HttpSession session) {
        session.invalidate();
        if(!userService.delete(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/login";
    }
}
