package com.example.uniclub05.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class SignupRequest {
    @NotNull (message = "Email can't null ")
    @NotBlank(message = "Email not blank")
    @Email
    private String email ;

    @NotNull (message = "Password can't null ")
    @NotBlank(message = "Password not blank")
    private String password ;
}
