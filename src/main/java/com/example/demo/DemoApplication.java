package com.example.demo;

import com.example.demo.dao.book.BookRepository;
import com.example.demo.dao.user.UserRepository;
import com.example.demo.model.book.Book;
import com.example.demo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Autowired
    BookRepository repository;

    @Autowired
    UserRepository repository1;

    @PostConstruct
    public void addData() {
        repository1.saveAll(Stream.of(new User("John"), new User("Sam")).collect(Collectors.toList()));
        repository.saveAll(Stream.of(new Book("Java"), new Book("Spring")).collect(Collectors.toList()));
    }
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return repository1.findAll();
    }
    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return repository.findAll();
    }
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
