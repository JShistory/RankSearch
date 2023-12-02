package com.example.Foods.member.controller;


import com.example.Foods.riotApi.entity.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @GetMapping("/member")
    public String showMember(){
        return "member/member";
    }

    @GetMapping("/test1")
    @ResponseBody
    public String test1(String name, int age){
        return "이름은" + name + "나이는" + age;
    }

    @GetMapping("/test")
    @ResponseBody
    public Test test2(Test test, String data){
        return test;
    }
}
