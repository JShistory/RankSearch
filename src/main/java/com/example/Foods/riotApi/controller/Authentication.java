package com.example.Foods.riotApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Authentication {
    @GetMapping("/riot.txt")
    @ResponseBody
    public String riotKey(){
        return "674e7e4b-9937-4b15-85c0-ade2e58616c3";
    }
}
