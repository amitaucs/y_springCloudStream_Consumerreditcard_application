package com.d2d.creditCard.gateway;

import com.d2d.creditCard.event.VerifyCreditCardEvent;
import com.d2d.creditCard.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@AllArgsConstructor
@Slf4j
@Configuration
public class CreditCardVerificationEventListener {

    private CreditCardService creditCardService;
    @Bean
    public Consumer<VerifyCreditCardEvent> generateCreditCard(){
        return verifyCreditCardEvent -> {
            log.info("Received credit card application : {}",
                    verifyCreditCardEvent.getCreditCardVerificationStatus().size() );
            creditCardService.generateCreditCardNumberAndCvv
                    (verifyCreditCardEvent.getCreditCardVerificationStatus());
        };
    }
}
