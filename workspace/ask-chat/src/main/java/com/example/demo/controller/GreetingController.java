package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ApiService;

@Controller
public class GreetingController {

    private final ApiService apiService;

    public GreetingController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/get-greeting")
    public String getGreeting(@RequestParam String name, Model model) {
        String greeting = apiService.getGreetingFromFlask(name);
        model.addAttribute("greeting", greeting);
        return "index";
    }
}
