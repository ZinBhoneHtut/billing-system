package com.zbh.billingsystem.service;

import com.zbh.billingsystem.entity.Transaction;
import com.zbh.billingsystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService implements CrudService<Transaction> {
    private final TransactionRepository transactionRepository;
    @Override
    public Optional<Transaction> findById(long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void update(long id, Transaction entity) throws Exception {
        //We should not update transaction
    }

    @Override
    public void deleteById(long id) {
        //We should not delete transaction
    }

    @Override
    public long count() {
        return transactionRepository.count();
    }
}
