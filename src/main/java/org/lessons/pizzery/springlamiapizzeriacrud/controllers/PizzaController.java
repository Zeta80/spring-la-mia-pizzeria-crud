package org.lessons.pizzery.springlamiapizzeriacrud.controllers;
import java.util.List;

import org.lessons.pizzery.springlamiapizzeriacrud.model.Pizza;
import org.lessons.pizzery.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;


    @GetMapping
        public String index(Model model) {
            List<Pizza> pizzas = pizzaRepository.findAll();
            model.addAttribute("pizzas", pizzas);
            return "pizzas/index";
        }
    }



