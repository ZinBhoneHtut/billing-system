package com.zbh.billingsystem.service;

import com.zbh.billingsystem.entity.Bill;
import com.zbh.billingsystem.exception.ResourceNotFoundException;
import com.zbh.billingsystem.repository.BillRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BillServiceTest {
    @InjectMocks
    private BillService billService;
    @Mock
    private BillRepository billRepository;
    private List<Bill> billList;
    private static final int NUMBER_OF_ENTITIES = 5;

    @BeforeEach
    void setUpMockData() {
        billList = IntStream.range(1, NUMBER_OF_ENTITIES + 1)
                .mapToObj(i -> Bill.builder().id(i).build())
                .collect(Collectors.toList());
    }

    @Test
    @Order(1)
    void shouldReturnBill_whenFindById() {
        long id = 1L;
        when(billRepository.findById(id)).thenReturn(billList.stream().filter(b -> b.getId() == id).findFirst());
        Bill resultBill = billService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found."));
        verify(billRepository).findById(id);
        verify(billRepository, times(1)).findById(id);
        assertEquals(id, resultBill.getId());
    }

    @Test
    @Order(2)
    void shouldNotPresentBillObject_whenFindByIdWithZero() {
        long id = 0L;
        when(billRepository.findById(id)).thenReturn(billList.stream().filter(b -> b.getId() == id).findFirst());
        Optional<Bill> bill = billService.findById(id);
        verify(billRepository).findById(id);
        verify(billRepository, times(1)).findById(id);
        assertThat("Bill object is present", bill.isPresent(), is(false));
    }

    @Test
    @Order(3)
    void shouldReturnBillList_whenFindAll() {
        when(billRepository.findAll()).thenReturn(billList);
        List<Bill> resultList = billService.findAll();
        verify(billRepository).findAll();
        assertNotNull(resultList);
        assertThat(resultList.size(), is(NUMBER_OF_ENTITIES));
    }

    @Test
    @Order(4)
    void whenFindAll_thenEmptyListIsReturned() {
        when(billRepository.findAll()).thenReturn(Collections.emptyList());
        List<Bill> resultList = billService.findAll();
        verify(billRepository).findAll();
        assertThat(resultList.size(), is(0));
    }
}
