package com.zbh.billingsystem.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zbh.billingsystem.dto.TransactionDTO;
import com.zbh.billingsystem.dto.Views;
import com.zbh.billingsystem.entity.Bill;
import com.zbh.billingsystem.entity.Transaction;
import com.zbh.billingsystem.exception.ResourceNotFoundException;
import com.zbh.billingsystem.mapper.TransactionMapper;
import com.zbh.billingsystem.service.BillService;
import com.zbh.billingsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final BillService billService;
    private final TransactionMapper transactionMapper;

    @PostMapping("/pay")
    @JsonView(value = Views.External.class)
    public ResponseEntity<TransactionDTO> payBill(@Valid @RequestBody TransactionDTO transactionDTO) {
        log.trace("Inside payBill method");
        Bill bill = billService.findById(transactionDTO.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Bill not found with id: " + transactionDTO.getId())
        );
        log.debug("Bill: {}", bill);
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction.setBill(bill);
        Transaction savedTransaction = transactionService.save(transaction);
        log.info("Transaction is saved successfully");
        log.debug("Transaction: {}", savedTransaction);
        TransactionDTO responseTransaction = transactionMapper.toDTO(savedTransaction);
        return new ResponseEntity<>(responseTransaction, HttpStatus.OK);
    }

    @GetMapping("/transaction")
    @JsonView(value = Views.Internal.class)
    public ResponseEntity<TransactionDTO> getTransaction(@RequestParam Long id) {
        log.trace("Inside getTransaction method.");
        Transaction transaction = transactionService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Transaction not found with id: " + id)
        );
        log.info("Transaction is retrieved successfully");
        log.debug("Transaction: {}", transaction);
        TransactionDTO transactionDTO = transactionMapper.toDTO(transaction);
        log.trace("TransactionDTO: {}", transactionDTO);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }
}

