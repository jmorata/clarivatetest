package com.jmorata.clarivatetest.controller;

import lombok.Builder;

@Builder
@lombok.Data
class RequestLogin {

    private String username;
    private String password;
}
