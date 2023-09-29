package com.zbh.billingsystem;

import com.zbh.billingsystem.entity.Bill;
import com.zbh.billingsystem.service.BillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class BillingSystemTests {

	@Autowired
	private BillService billService;

	@Test
	void testFindAll() {
		List<Bill> billList = billService.findAll();
		assertThat(billList.size(), is(greaterThanOrEqualTo(0)));
	}

}
