package com.local.openbank.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty("user_id")
    private String userId;
}
