package org.lessons.pizzery.springlamiapizzeriacrud.controllers;
import java.util.List;
import java.util.Optional;

import org.lessons.pizzery.springlamiapizzeriacrud.model.Pizza;
import org.lessons.pizzery.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;


    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("list", pizzas);
        return "pizzas/index";
    }



    @GetMapping("/{pizzaId}")
    public String show(@PathVariable("pizzaId") Integer id, Model model){
        Optional<Pizza> result = pizzaRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("pizza", result.get());
            return "pizzas/show";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}



