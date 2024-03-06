package com.example.Foods.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v2")
@RestController
public class RiotV2ApiController {

    @GetMapping("/summoner/{name}")
    public String summoner(@PathVariable String name){
        return "hello";
    }
}
