package org.lessons.pizzery.springlamiapizzeriacrud.controllers;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.lessons.pizzery.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.pizzery.springlamiapizzeriacrud.model.Pizza;
import org.lessons.pizzery.springlamiapizzeriacrud.repository.PizzaRepository;
import org.lessons.pizzery.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {


    @Autowired
    private PizzaService pizzaService;




    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
        List<Pizza> pizzas;
        if (keyword.isEmpty()) {
            pizzas = pizzaService.getAllPizzas();
        } else {
            pizzas = pizzaService.getFilteredPizzas(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", pizzas);

        return "/pizzas/index";
    }





    @GetMapping("/{pizzaId}")
    public String show(@PathVariable("pizzaId") Integer id, Model model) {
        try {
            Pizza pizza = pizzaService.getById(id);
            model.addAttribute("pizza", pizza);
            return "/pizzas/show";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
        }

    }



    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza,
      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "/pizzas/create";
        }
        pizzaService.createPizza(formPizza);
        return "redirect:/pizzas";

    }
}



