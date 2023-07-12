package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.MaterialsProductionCreateEditDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.dto.WorkerReadDto;
import by.kozlov.spring.service.BrigadeService;
import by.kozlov.spring.service.MaterialService;
import by.kozlov.spring.service.MaterialsProductionService;
import by.kozlov.spring.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/materialsProduction")
@RequiredArgsConstructor
@SessionAttributes("user")
public class MaterialsProductionController {

    private final MaterialsProductionService materialsProductionService;
    private final WorkerService workerService;
    private final BrigadeService brigadeService;
    private final MaterialService materialService;

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
                         Model model,
                         @ModelAttribute("user") UserReadDto user){

        var materialProduction = materialsProductionService.findById(id).orElseThrow();
        var materials = materialService.findAll();
        model.addAttribute("materials",materials);
        model.addAttribute("materialProduction",materialProduction);
        if(user.getRole().equals(Role.ADMIN)) {
            var brigades = brigadeService.findAll();
            model.addAttribute("brigades",brigades);
            return "admin/adminEditMaterialsProduction";
        } else {
            var worker = workerService.findByEmail(user.getEmail()).orElseThrow();
            model.addAttribute("worker",worker);
            return "worker/workerEditMaterialsProduction";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute("materialProduction") MaterialsProductionCreateEditDto material,
                         @ModelAttribute("user") UserReadDto user) {


        return materialsProductionService.update(id,material)
                .map(it -> {
                    if(user.getRole().equals(Role.ADMIN)) {
                            return "redirect:/materialsProduction";
                    } else {
                        var worker = workerService.findByEmail(user.getEmail()).orElseThrow();
                        return "redirect:/materialsProduction/" + worker.getId();
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id,
                         @ModelAttribute("user") UserReadDto user) {


        if(!materialsProductionService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(user.getRole().equals(Role.ADMIN)) {
            return "redirect:/materialsProduction";
        } else {
            var worker = workerService.findByEmail(user.getEmail()).orElseThrow();
            return "redirect:/materialsProduction/" + worker.getId();
        }
    }

    @GetMapping("/createMaterialProduction")
    public String createMaterialProduction(Model model, @ModelAttribute MaterialsProductionCreateEditDto materialProduction) {
        var brigades = brigadeService.findAll();
        var materials = materialService.findAll();
        model.addAttribute("brigades",brigades);
        model.addAttribute("materials",materials);
        model.addAttribute("materialProduction",materialProduction);
        return "admin/adminCreateMaterialsProduction";
    }

    @PostMapping("/createMaterialProduction")
    public String createMaterialProduction(@ModelAttribute @Validated MaterialsProductionCreateEditDto materialProduction,
                                           BindingResult bindingResult,
                                           RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("materialProduction",materialProduction);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/materialsProduction/createMaterialProduction";
        }
        materialsProductionService.create(materialProduction);
        return "redirect:/materialsProduction";
    }

    @GetMapping("/{id}/createMaterialProductionForWorker")
    public String createMaterialProductionForWorker(@PathVariable("id") Integer id,
                                                    Model model,
                                                    @ModelAttribute MaterialsProductionCreateEditDto materialProduction) {
        var worker = workerService.findById(id).orElseThrow();
        var materials = materialService.findAll();
        model.addAttribute("worker",worker);
        model.addAttribute("materials",materials);
        model.addAttribute("materialProduction",materialProduction);
        return "worker/workerCreateMaterialsProduction";
    }

    @PostMapping("/{id}/createMaterialProductionForWorker")
    public String createMaterialProductionForWorker(@PathVariable("id") Integer id,
                                                    @ModelAttribute @Validated MaterialsProductionCreateEditDto materialProduction,
                                                    BindingResult bindingResult,
                                                    RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("materialProduction",materialProduction);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/materialsProduction/" + id + "/createMaterialProductionForWorker";
        }

        materialsProductionService.create(materialProduction);
        return "redirect:/materialsProduction/" + id;
    }
}
