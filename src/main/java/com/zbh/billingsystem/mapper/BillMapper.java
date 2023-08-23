package com.zbh.billingsystem.mapper;

import com.zbh.billingsystem.dto.BillDTO;
import com.zbh.billingsystem.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillMapper extends BaseMapper<Bill, BillDTO> {

    @Mapping(target = "id", source = "billId")
    Bill toEntity(BillDTO billDTO);

    @Mapping(target = "billId", source = "id")
    BillDTO toDTO(Bill bill);

}
