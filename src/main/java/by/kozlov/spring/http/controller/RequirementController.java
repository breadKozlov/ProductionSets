package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.RequirementCreateEditDto;
import by.kozlov.spring.dto.RequirementReadDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/requirement")
@RequiredArgsConstructor
public class RequirementController {

    private final RequirementService requirementService;

    @GetMapping
    public String findAll(Model model) {

        var sort = Sort.by("id");
        var requirementList = requirementService.findAll(sort);
        model.addAttribute("requirementList",requirementList);
        return "admin/adminRequirement";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, Model model) {

        var requirement = requirementService.findById(id).orElseThrow();
        model.addAttribute("requirement",requirement);
        return "admin/adminEditRequirement";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("requirement") RequirementCreateEditDto req) {
        return requirementService.update(id,req)
                .map(it -> {
                    return "redirect:/requirement";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
