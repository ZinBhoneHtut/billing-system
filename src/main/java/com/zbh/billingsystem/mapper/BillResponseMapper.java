package com.zbh.billingsystem.mapper;

import com.zbh.billingsystem.dto.BillDTO;
import com.zbh.billingsystem.dto.BillResponseDTO;
import com.zbh.billingsystem.utils.DateUtils;
import org.mapstruct.*;

import java.util.Date;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BillResponseMapper {
    @Mapping(target = "statusMessage", constant = "Transaction is successful!")
    @BeanMapping(builder = @Builder(disableBuilder = true))
    BillResponseDTO toBillReponseDTO(BillDTO billDTO);

    @AfterMapping
    default void setDateTime(@MappingTarget BillResponseDTO billResponseDTO) {
        billResponseDTO.setDateTime(DateUtils.format(new Date(), "yyyyMMddHHmmss"));
    }

}
