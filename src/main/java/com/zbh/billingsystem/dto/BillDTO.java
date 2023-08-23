package com.zbh.billingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"billId", "name"})
public class BillDTO {

    @JsonProperty(value = "bill_id")
    private String billId;

    @NotNull(message = "Bill name cannot be empty")
    @NotBlank(message = "Bill name cannot be empty")
    private String name;

    private String description;

}
