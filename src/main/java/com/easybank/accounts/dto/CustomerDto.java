package com.easybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold  customer and Account Information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the Customer",
            example = "sunil"
    )
    @NotEmpty(message = "Name can not be null")
    @Size(min = 5, max = 30, message = "the length of Customer name should be 5 to 30")
    private String name;

    @NotEmpty(message = "Email can not be null")
    @Email(message = "Email address should be valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digit")
    private String mobileNumber;

    private AccountsDto accounts;
}
