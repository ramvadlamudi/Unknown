package com.proj.donemcd.dto;

import lombok.Data;

@Data
public class Response {
    private int errorCode;
    private String message;

    public Response() {
    }

    public Response(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
