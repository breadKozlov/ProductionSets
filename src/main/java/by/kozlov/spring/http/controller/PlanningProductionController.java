package by.kozlov.spring.http.controller;

import by.kozlov.spring.service.DifferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planningProduction")
@RequiredArgsConstructor
public class PlanningProductionController {

    private final DifferenceService differenceService;
    @GetMapping
    public String findAll(Model model) {
        var materials = differenceService.findAllDifferenceProductionMaterials();
        var kits = differenceService.findAllDifferenceProductionSets();
        model.addAttribute("materials",materials);
        model.addAttribute("kits",kits);
        return "admin/adminPlanningProduction";
    }
}
