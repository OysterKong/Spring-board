package com.oyster.kong.controller;

import com.oyster.kong.domain.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimpleRestController {
//    @GetMapping("/ajax")
//    public String ajax() {
//        return "ajax";
//    }
	
    @GetMapping("/test")
    public String test() {
    	return "commentsample";
    }

//    @PostMapping("/send")
//    @ResponseBody
//    public Person test(@RequestBody Person p) {
//        System.out.println("p = " + p);
//        p.setName("ABC");
//        p.setAge(p.getAge() + 10);
//
//        return p;
//    }
}