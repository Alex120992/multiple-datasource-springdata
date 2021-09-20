package com.example.demo.service;

import com.example.demo.dao.book.BookRepository;
import com.example.demo.dao.user.UserRepository;
import com.example.demo.model.book.Book;
import com.example.demo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DaoService {
    @Autowired
    BookRepository repository;

    @Autowired
    UserRepository repository1;

    @Transactional(transactionManager = "userTransactionManager")
    public void addData1() {
        repository1.saveAll(Stream.of(new User("John"), new User("Sam")).collect(Collectors.toList()));
    }

    @Transactional(transactionManager = "bookTransactionManager")
    public void addData2() {
        repository.saveAll(Stream.of(new Book("Java"), new Book("Spring")).collect(Collectors.toList()));
    }
}
