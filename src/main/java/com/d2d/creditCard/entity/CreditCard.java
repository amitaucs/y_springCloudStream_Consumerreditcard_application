package com.d2d.creditCard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_CREDIT_CARD_DETAILS")
public class CreditCard {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "REFERENCE_ID")
    private String refId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "APPLICATION_VERIFICATION_STATUS")
    private String status;
    @Column(name = "CREDIT_CARD_NUMBER")
    private Long creditCardNumber;
    @Column(name = "CREDIT_CARD_CVV")
    private Long cvv;

}
