package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.ProductionCreateEditDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.service.ProductionService;
import by.kozlov.spring.service.SetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Controller
@RequestMapping("/production")
@RequiredArgsConstructor
@SessionAttributes("user")
public class ProductionController {

    private final ProductionService productionService;
    private final SetService setService;


    @GetMapping
    public String findAll(Model model, @ModelAttribute("user") UserReadDto user) {
        if(user.getRole().equals(Role.ADMIN)) {
            var page = PageRequest.of(0,
                    100,
                    Sort.by("dateOfProduction"));
            model.addAttribute("production",productionService.findAll(page));
            return "admin/adminProduction";
        } else {
            return "user/userProduction";
        }
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, Model model) {

        var product = productionService.findById(id).orElseThrow();
        var kits = setService.findAll();
        model.addAttribute("product",product);
        model.addAttribute("kits",kits);
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

    @GetMapping("{id}/createProductForWorker")
    public String createProductForWorker(@PathVariable("id") Integer id, Model model,
                                         @ModelAttribute ProductionCreateEditDto product) {
        var kits = setService.findAll();
        model.addAttribute("kits",kits);
        model.addAttribute("workerId",id);
        model.addAttribute("product",product);
        model.addAttribute("localDate", LocalDate.now());
        return "/admin/adminCreateWorkerProduction";
    }

    @PostMapping("/createProductForWorker")
    public String createProductForWorker(@ModelAttribute ProductionCreateEditDto product) {
        return "redirect:/admins/worker/" + productionService.create(product).getWorker().getId();
    }
}
