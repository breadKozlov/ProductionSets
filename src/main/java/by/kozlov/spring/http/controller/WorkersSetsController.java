package by.kozlov.spring.http.controller;

import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.dto.WorkersSetsCreateEditDto;
import by.kozlov.spring.service.SetService;
import by.kozlov.spring.service.WorkersSetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/workersSets")
@RequiredArgsConstructor
@SessionAttributes("user")
public class WorkersSetsController {

    private final SetService setService;
    private final WorkersSetsService workersSetsService;
    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, Model model,
                         @ModelAttribute("user") UserReadDto user) {
        var setOfWorker = workersSetsService.findById(id).orElseThrow(); //текущий ентити для редактирования
        var currentSet = setOfWorker.getSet(); //текущий комплект вытянутый из ентити
        var allSetsOfWorker = workersSetsService.findAllByWorkerId(user.getId()); //все запланированные комплекты работника
        var freeSets = setService.findAllFreeSets(allSetsOfWorker); //комплекты которые не вошли в план
        freeSets.add(currentSet);//плюс текущий комплект в список не запланированных комплектов
        model.addAttribute("kits",freeSets);
        model.addAttribute("setOfWorker",setOfWorker);
        return "worker/workerEditSetOfWorker";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute("setOfWorker") WorkersSetsCreateEditDto setOfWorker) {
        return workersSetsService.update(id,setOfWorker)
                .map(it -> "redirect:/worker")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {

        if(!workersSetsService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/worker";
    }

    @GetMapping("/{id}/createSetForWorker")
    public String createSetForWorker (@PathVariable("id") Integer id, Model model,
                                      @ModelAttribute WorkersSetsCreateEditDto setOfWorker) {

        var allSetsOfWorker = workersSetsService.findAllByWorkerId(id);
        var freeSets = setService.findAllFreeSets(allSetsOfWorker);
        model.addAttribute("workerId",id);
        model.addAttribute("kits",freeSets);
        model.addAttribute("setOfWorker",setOfWorker);
        return "worker/workerCreateSetOfWorker";
    }

    @PostMapping("/createSetForWorker")
    public String createSetForWorker(@ModelAttribute WorkersSetsCreateEditDto setOfWorker) {
        workersSetsService.create(setOfWorker);
        return "redirect:/worker";
    }
}
