package com.zbh.billingsystem.mapper;

import com.zbh.billingsystem.dto.TransactionDTO;
import com.zbh.billingsystem.entity.Transaction;
import com.zbh.billingsystem.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TransactionMapper extends BaseMapper<Transaction, TransactionDTO> {

    @Mapping(target = "statusMessage", constant = "Transaction is successful!")
    @Mapping(target = "transactionDate", source = "audit.createdDate", qualifiedByName = "formatDate")
    @Mapping(target = "transactionId", source = "id")
    TransactionDTO toDTO(Transaction entity);

    @Mapping(target = "id", ignore = true)
    Transaction toEntity(TransactionDTO dto);

    @Named("formatDate")
    public static String formatDate(Date date) {
        return DateUtils.format(date, "yyyyMMddHHmmss");
    }
}
