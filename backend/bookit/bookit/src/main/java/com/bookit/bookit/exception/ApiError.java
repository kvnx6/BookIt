package com.bookit.bookit.exception;


import lombok.*;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private List<FieldValidationError> fieldErrors;

    @Getter @Setter
    @AllArgsConstructor
    public static class FieldValidationError {
        private String field;
        private String error;
    }

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
