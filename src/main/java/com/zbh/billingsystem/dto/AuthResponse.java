package com.zbh.billingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    @JsonProperty(value = "status_message")
    private String statusMessage;

    @JsonProperty(value = "access_token")
    private String accessToken;
}
