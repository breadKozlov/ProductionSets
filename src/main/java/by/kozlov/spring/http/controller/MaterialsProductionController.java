package by.kozlov.spring.http.controller;

import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.service.BrigadeService;
import by.kozlov.spring.service.MaterialService;
import by.kozlov.spring.service.MaterialsProductionService;
import by.kozlov.spring.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materialsProduction")
@RequiredArgsConstructor
@SessionAttributes("user")
public class MaterialsProductionController {

    private final MaterialsProductionService materialsProductionService;
    private final WorkerService workerService;

    @GetMapping
    public String findAll(Model model) {
        var page = PageRequest.of(0,100, Sort.by("dateOfProduction"));
        var materials = materialsProductionService.findAll(page);
        model.addAttribute("materials",materials);
        return "admin/adminMaterialsProduction";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {

        var worker = workerService.findById(id).orElseThrow();
        var page = PageRequest.of(0,100, Sort.by("dateOfProduction"));
        var materials = materialsProductionService.findAllByBrigadeId(worker.getBrigade().getId(),page);
        model.addAttribute("worker",worker);
        model.addAttribute("materials",materials);
        return "worker/workerReleaseReport";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute("user") UserReadDto user){

        return "admin/adminEditMaterialsProduction";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id) {
        return "redirect:/materialsProduction";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {

        return "view";
    }
}
