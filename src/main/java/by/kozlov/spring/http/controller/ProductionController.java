package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.ProductionCreateEditDto;
import by.kozlov.spring.dto.UserReadDto;
import by.kozlov.spring.service.ProductionService;
import by.kozlov.spring.service.SetService;
import by.kozlov.spring.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/production")
@RequiredArgsConstructor
@SessionAttributes("user")
public class ProductionController {

    private final ProductionService productionService;
    private final SetService setService;
    private final WorkerService workerService;


    @GetMapping
    public String findAll(Model model, @ModelAttribute("user") UserReadDto user) {
        if(user.getRole().equals(Role.ADMIN)) {
            var page = PageRequest.of(0,
                    100,
                    Sort.by("dateOfProduction"));
            model.addAttribute("flag", "flag");//для разделения редиректов в рамках одного контроллера
            model.addAttribute("production",productionService.findAll(page));
            return "admin/adminProduction";
        } else {
            return "user/userProduction";
        }
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, Model model,
                         @ModelAttribute("flag") String flag) {

        var product = productionService.findById(id).orElseThrow();
        var kits = setService.findAll();
        model.addAttribute("product",product);
        model.addAttribute("kits",kits);
        if (flag != null) {
            var workers = workerService.findAll();
            model.addAttribute("workers",workers);
            return "admin/adminEditProduction";
        } else {
            return "admin/adminEditWorkerProduction";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute("product") ProductionCreateEditDto product,
                         @ModelAttribute("flag") String flag) {

        return productionService.update(id,product)
                .map(it -> {
                    if (flag != null) {
                        return "redirect:/production";
                    } else {
                         return "redirect:/admin/worker/" + product.getWorkerId();
                        }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, @ModelAttribute("flag") String flag) {

        var workerId = productionService.findById(id).orElseThrow().getWorker().getId();
        if(!productionService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (flag != null) {
            return "redirect:/production";
        } else {
            return "redirect:/admin/worker/" + workerId;
        }
    }

    @GetMapping("{id}/createProductForWorker")
    public String createProductForWorker(@PathVariable("id") Integer id, Model model,
                                         @ModelAttribute ProductionCreateEditDto product) {
        var kits = setService.findAll();
        model.addAttribute("kits",kits);
        model.addAttribute("workerId",id);
        model.addAttribute("product",product);
        return "admin/adminCreateWorkerProduction";
    }

    @PostMapping("/createProductForWorker")
    public String createProductForWorker(@ModelAttribute ProductionCreateEditDto product) {
        return "redirect:/admin/worker/" + productionService.create(product).getWorker().getId();
    }

    @GetMapping("/createProduct")
    public String createProduct(Model model, @ModelAttribute ProductionCreateEditDto product) {

        var workers = workerService.findAll();
        var kits = setService.findAll();
        model.addAttribute("workers",workers);
        model.addAttribute("kits",kits);
        model.addAttribute("product",product);
        return "admin/adminCreateProduction";

    }

    @PostMapping("/createProduct")
    public String createProduct(@ModelAttribute ProductionCreateEditDto product) {
        productionService.create(product);
        return "redirect:/production";
    }
}
