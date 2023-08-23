package com.zbh.billingsystem.service;

import com.zbh.billingsystem.entity.Bill;
import com.zbh.billingsystem.exception.ResourceNotFoundException;
import com.zbh.billingsystem.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService implements CrudService<Bill> {
    private final BillRepository billRepository;

    @Override
    public Optional<Bill> findById(long id) {
        return billRepository.findById(id);
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void update(long id, Bill bill) throws Exception {
        Bill billFromDB = this.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Bill with id " + id + " not found")
        );
        billFromDB.setName(bill.getName());
        billFromDB.setDescription(bill.getDescription());
    }

    @Override
    public void deleteById(long id) {
        billRepository.deleteById(id);
    }

    @Override
    public long count() {
        return billRepository.count();
    }

    public void saveAll(Iterable<Bill> bills) {
        billRepository.saveAll(bills);
    }
}
