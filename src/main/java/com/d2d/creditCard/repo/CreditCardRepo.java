package com.d2d.creditCard.repo;

import com.d2d.creditCard.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepo extends JpaRepository<CreditCard,Long> {
    CreditCard findByRefId(String applicationRefId);
}
