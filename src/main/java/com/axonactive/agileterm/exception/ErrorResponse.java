package com.axonactive.agileterm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
