package com.zbh.billingsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"statusMessage", "dateTime", "billId"})
public class BillResponseDTO {
    @JsonProperty(value = "status_message")
    private String statusMessage;

    @JsonProperty(value = "date_time")
    private String dateTime;

    @JsonProperty(value = "bill_id")
    private String billId;

    private String name;

    private String description;

    private List<BillDTO> billers;

}
