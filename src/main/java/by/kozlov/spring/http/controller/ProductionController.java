package by.kozlov.spring.http.controller;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.dto.*;
import by.kozlov.spring.service.ProductionService;
import by.kozlov.spring.service.SetService;
import by.kozlov.spring.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public String findAll(Model model, ProductionFilter filter, Pageable pageable,
                          @ModelAttribute("user") UserReadDto user) {

//        var page = PageRequest.of(0,
//                    100,
//                    Sort.by("dateOfProduction"));
        Page<ProductionReadDto> page = productionService.findAll(filter,pageable);
        var workers = workerService.findAll();
        var kits = setService.findAll();
        model.addAttribute("workers",workers);
        model.addAttribute("kits",kits);
        model.addAttribute("flag", "flag");//для разделения редиректов в рамках одного контроллера
        model.addAttribute("production", PageResponse.of(page));
        model.addAttribute("filter",filter);
        return "admin/adminProduction";

    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, Model model,
                         @ModelAttribute("flag") String flag,
                         @ModelAttribute("user") UserReadDto user) {

        var product = productionService.findById(id).orElseThrow();
        var kits = setService.findAll();
        model.addAttribute("product",product);
        model.addAttribute("kits",kits);
        if(user.getRole().equals(Role.ADMIN)) {
            if (flag.equals("flag")) {
                var workers = workerService.findAll();
                model.addAttribute("workers",workers);
                return "admin/adminEditProduction";
            } else {
                return "admin/adminEditWorkerProduction";
            }
        } else {
            return "worker/workerEditProduction";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute("product") ProductionCreateEditDto product,
                         @ModelAttribute("flag") String flag,
                         @ModelAttribute("user") UserReadDto user) {

        return productionService.update(id,product)
                .map(it -> {
                    if(user.getRole().equals(Role.ADMIN)) {
                        if (flag.equals("flag")) {
                            return "redirect:/production";
                        } else {
                            return "redirect:/admin/worker/" + product.getWorkerId();
                        }
                    } else {
                        return "redirect:/worker";
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, @ModelAttribute("flag") String flag,
                         @ModelAttribute("user") UserReadDto user) {

        var workerId = productionService.findById(id).orElseThrow().getWorker().getId();
        if(!productionService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(user.getRole().equals(Role.ADMIN)) {
            if (flag.equals("flag")) {
                return "redirect:/production";
            } else {
                return "redirect:/admin/worker/" + workerId;
            }
        } else {
            return "redirect:/worker";
        }
    }

    @GetMapping("{id}/createProductForWorker")
    public String createProductForWorker(@PathVariable("id") Integer id, Model model,
                                         @ModelAttribute ProductionCreateEditDto product,
                                         @ModelAttribute("user") UserReadDto user) {
        var kits = setService.findAll();
        model.addAttribute("kits",kits);
        model.addAttribute("workerId",id);
        model.addAttribute("product",product);
        if(user.getRole().equals(Role.ADMIN)) {
            return "admin/adminCreateWorkerProduction";
        } else {
            return "worker/workerCreateProduction";
        }

    }

    @PostMapping("/createProductForWorker")
    public String createProductForWorker(@ModelAttribute @Validated ProductionCreateEditDto product,
                                         @ModelAttribute("user") UserReadDto user) {

        if(user.getRole().equals(Role.ADMIN)) {
            return "redirect:/admin/worker/" + productionService.create(product).getWorker().getId();
        } else {
            productionService.create(product);
            return "redirect:/worker";
        }

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
    public String createProduct(@ModelAttribute @Validated ProductionCreateEditDto product,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product",product);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/production/createProduct";
        }
        productionService.create(product);
        return "redirect:/production";
    }
}
