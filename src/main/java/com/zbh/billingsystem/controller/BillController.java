package com.zbh.billingsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zbh.billingsystem.dto.BillDTO;
import com.zbh.billingsystem.dto.BillResponseDTO;
import com.zbh.billingsystem.entity.Bill;
import com.zbh.billingsystem.exception.ResourceNotFoundException;
import com.zbh.billingsystem.mapper.BillMapper;
import com.zbh.billingsystem.mapper.BillResponseMapper;
import com.zbh.billingsystem.service.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;
    private final BillMapper billMapper;
    private final BillResponseMapper billResponseMapper;

    @PostMapping("/add")
    public ResponseEntity<BillResponseDTO> addBill(@Valid @RequestBody BillDTO billDTO) {
        log.trace("Inside addBill method");
        Bill bill = billService.save(billMapper.toEntity(billDTO));
        log.info("Bill is saved successfully.");
        log.debug("Bill: {}", bill);
        BillDTO createdBill = billMapper.toDTO(bill);
        BillResponseDTO billResponseDTO = billResponseMapper.toBillReponseDTO(createdBill);
        billResponseDTO.setStatusMessage(String.format("Bill %s is successfully saved in the system.", bill.getName()));
        return new ResponseEntity<>(billResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<BillResponseDTO> getBill(@RequestParam(required = false) Long id) throws JsonProcessingException {
        log.trace("Inside getBill method.");
        BillResponseDTO billResponseDTO;
        if (ObjectUtils.isNotEmpty(id)) {
            BillDTO billDTO = billMapper.toDTO(billService.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Bill with id " + id + " not found")));
            log.info("Bill is retrieved successfully by id.");
            log.debug("BillDTO: {}", billDTO);
            billResponseDTO = billResponseMapper.toBillReponseDTO(billDTO);
        } else {
            List<BillDTO> billDTOList = billMapper.toDTO(billService.findAll());
            log.info("All bills is retrieved successfully");
            billResponseDTO = billResponseMapper.toBillReponseDTO(new BillDTO());
            billResponseDTO.setBillers(billDTOList);
        }
        return new ResponseEntity<>(billResponseDTO, HttpStatus.OK);
    }
}
