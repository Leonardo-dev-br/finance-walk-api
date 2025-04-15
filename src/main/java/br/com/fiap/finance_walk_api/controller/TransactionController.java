package br.com.fiap.finance_walk_api.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.finance_walk_api.model.Transaction;
import br.com.fiap.finance_walk_api.model.TransactionFilter;
import br.com.fiap.finance_walk_api.repository.TransactionRepository;
import br.com.fiap.finance_walk_api.repository.specification.TransactionSpecification;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {


    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(TransactionFilter filter) {
       
        return repository.findAll(TransactionSpecification.withFilters(filter));
    }

}
