package by.kozlov.spring.http.controller;

import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.dto.WorkerCreateEditDto;
import by.kozlov.spring.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping()
    public String findWorker(Model model, @ModelAttribute("user")UserReadDto user) {

        Sort sort = Sort.by("dateOfProduction");
        var worker = workerService.findByEmail(user.getEmail()).orElseThrow();
        var setsOfWorker = workersSetsService.findAllByWorkerId(worker.getId());
        var releasedSets = productionService.findAllByWorkerId(worker.getId(),sort);
        model.addAttribute("worker",worker);
        model.addAttribute("setsOfWorker",setsOfWorker);
        model.addAttribute("releasedSets",releasedSets);
        model.addAttribute("diffSets",differenceService.findAllDifferenceWorkerSets(worker.getId()));

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
    public String create(@ModelAttribute WorkerCreateEditDto worker,
                         RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(workerService.create(worker));
        return "redirect:/worker";
    }
}
