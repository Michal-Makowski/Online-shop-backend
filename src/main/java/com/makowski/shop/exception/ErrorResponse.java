package com.makowski.shop.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timesptamp;
    private List<String> message;
    
    public ErrorResponse(List<String> message) {
        this.timesptamp = LocalDateTime.now();
        this.message = message;
    }

    
}
