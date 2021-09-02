package com.d2d.creditCard.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VerifyCreditCardEvent {

    private VerifyCreditCardEvent.eventType event = VerifyCreditCardEvent.eventType.Approve_Credit_Card;
    private List<CreditCardVerificationStatus> creditCardVerificationStatus;

    enum eventType{
        Approve_Credit_Card
    }
}
