package com.example.shoppinglist.controllers;

import com.example.shoppinglist.persist.ShoppingItem;
import com.example.shoppinglist.persist.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.util.Optional;

@Controller
public class ShoppingListController {
    @Autowired
    private EntityManager entityManager;


    private ShoppingItemRepository repository;
    @Autowired
    public ShoppingListController(ShoppingItemRepository repository) {
        this.repository = repository;
    }


    @GetMapping("")
    public String indexPage(Model model){
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", new ShoppingItem());
        return "index";

    }
    @PostMapping
    public String newShoppingItem(ShoppingItem shoppingItem) {
        repository.save(shoppingItem);
        return "redirect:/";
    }
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        repository.deleteById(id);
//
//
//        return "redirect:/";
//    }
//    @GetMapping("/getOne")
//    @ResponseBody
//    public Optional<ShoppingItem> getOne(Long id) {
//        return repository.findById(id);
//    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);

        return "redirect:/";
    }
    
}
