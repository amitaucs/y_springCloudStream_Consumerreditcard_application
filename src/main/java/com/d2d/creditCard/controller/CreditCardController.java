package com.d2d.creditCard.controller;


import com.d2d.creditCard.service.CreditCardService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/creditCardGeneration/v1")
@Tag(name = "Credit Card")
@Api(tags = {"Credit Card"})
public class CreditCardController {

    private CreditCardService creditCardService;

    @ApiOperation(value = "Credit Card Application Status")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 200, message = "OK", response = String.class)})

    @GetMapping("/creditCardApplicationStatus")
    public ResponseEntity<String> creditCardApplicationStatus(
            @ApiParam(value = "Credit Card Application Reference Id", type = "String", required = true)
            @RequestParam String applicationRefId) {

        if (StringUtils.isEmpty(applicationRefId)) {
            ResponseEntity.badRequest().build();
        }
        var status = creditCardService.getApplicationStatus(applicationRefId);

        return StringUtils.isEmpty(status) ? ResponseEntity.status(HttpStatus.OK)
                .body("Your application is still under progress")
                : ResponseEntity.status(HttpStatus.OK).body("Your application with reference Id "
                + applicationRefId + "has been " + status);
    }
}
