package by.kozlov.spring.http.controller;

import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.service.DifferenceService;
import by.kozlov.spring.service.ProductionService;
import by.kozlov.spring.service.WorkerService;
import by.kozlov.spring.service.WorkersSetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/worker")
@RequiredArgsConstructor
@SessionAttributes("user")
public class WorkerController {

    private final WorkerService workerService;
    private final WorkersSetsService workersSetsService;
    private final ProductionService productionService;
    private final DifferenceService differenceService;

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
}
