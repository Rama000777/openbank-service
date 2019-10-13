package com.local.openbank.clientapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.local.openbank.model.Token;

@FeignClient(name="openBank", url="${openbank.api.rootUrl}")
public interface DirectAuthenticationClient {

    @PostMapping(value = "${openbank.api.directLoginPath}")
    Token loginInternal(@RequestHeader("Authorization") String authHeader);

    default String login(String username, String password, String consumerKey) {
        return loginInternal(String.format("DirectLogin username=%s,password=%s,consumer_key=%s", username, password, consumerKey)).getToken();
    }
}
