package com.example.demo.controller;

import com.example.demo.service.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    DaoService daoService;

    @GetMapping("/add1")
    public void addData1() {
        daoService.addData1();
    }

    @GetMapping("/add2")
    public void addData2() {
        daoService.addData2();
    }
//    @GetMapping("/getUsers")
//    public List<User> getUsers(){
////        return repository1.findAll();
//    }
//    @GetMapping("/getBooks")
//    public List<Book> getBooks() {
//        return repository.findAll();
//    }
}
