package com.zbh.billingsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TransactionDTO {
    @JsonView(Views.External.class)
    @JsonProperty(value = "status_message")
    private String statusMessage;

    @JsonProperty(value = "transaction_id")
    @JsonView(Views.External.class)
    private long transactionId;

    @JsonProperty(value = "transaction_date")
    @JsonView(Views.External.class)
    private String transactionDate;

    @JsonView(Views.Internal.class)
    @JsonProperty(value = "api_caller")
    @NotBlank(message = "Api caller cannot be empty")
    private String apiCaller;

    @NotNull(message = "Id cannot be empty")
    @JsonView(Views.Internal.class)
    private Long id;

    @NotNull(message = "Amount cannot be empty")
    @Max(value = 100000, message = "Amount must be less than or equal {value}")
    private Long amount;

    @JsonView(value = {Views.Internal.class})
    @JsonProperty(value = "reference_no")
    @NotBlank(message = "Reference no cannot be empty")
    private String referenceNo;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "959\\d+", message = "Phone number must start with '959' and consist of only digits")
    @Size(min = 10, max = 12, message = "Phone number must be between {min} or {max}")
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
}
