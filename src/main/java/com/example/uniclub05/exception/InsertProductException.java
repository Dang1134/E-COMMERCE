package com.example.uniclub05.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InsertProductException extends RuntimeException {
    private String massage ;

}
