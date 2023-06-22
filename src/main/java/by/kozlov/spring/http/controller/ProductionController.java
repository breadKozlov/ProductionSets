package by.kozlov.spring.http.controller;

import by.kozlov.spring.dto.ProductionCreateEditDto;
import by.kozlov.spring.service.ProductionService;
import by.kozlov.spring.service.SetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionService productionService;
    private final SetService setService;


    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, Model model) {

        var product = productionService.findById(id).orElseThrow();
        var workerId = product.getWorker().getId();
        var kits = setService.findAll();
        model.addAttribute("product",product);
        model.addAttribute("kits",kits);
        model.addAttribute("workerId",workerId);
        return "/admin/adminEditWorkerProduction";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("product") ProductionCreateEditDto product) {
        return productionService.update(id,product)
                .map(it -> "redirect:/admins/worker/" + product.getWorkerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {

        var workerId = productionService.findById(id).orElseThrow().getWorker().getId();
        if(!productionService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/admins/worker/" + workerId;
    }
}
