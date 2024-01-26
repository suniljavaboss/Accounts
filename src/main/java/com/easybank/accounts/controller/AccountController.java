package com.easybank.accounts.controller;

import com.easybank.accounts.constants.AccountConstants;
import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.dto.ErrorResponseDto;
import com.easybank.accounts.dto.ResponseDto;
import com.easybank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "curd REST Api for Account in easyBank",
        description = "CURD REST APIs in easyBank to create, update, fetch and delete accounts details"
)
@RestController
@RequestMapping("/com/account")
@Validated
@AllArgsConstructor
public class AccountController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "create accounts REST API",
            description = "REST API to create new customer & account inside EasyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccounts(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){

       CustomerDto customerDto =iAccountsService.fetchAccount(mobileNumber);

       return ResponseEntity
               .status(HttpStatus.OK).body(customerDto);
    }


    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )

            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){

        boolean isUpdated=iAccountsService.updateAccount(customerDto);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500,AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber){

        boolean isDeleted=iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500,AccountConstants.MESSAGE_500));
        }
    }

}
