package com.d2d.creditCard.service;

import com.d2d.creditCard.entity.CreditCard;
import com.d2d.creditCard.event.CreditCardVerificationStatus;
import com.d2d.creditCard.repo.CreditCardRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CreditCardService {

    private CreditCardRepo creditCardRepo;

    public void generateCreditCardNumberAndCvv
            (List<CreditCardVerificationStatus> creditCardVerificationStatus) {

       var creditCards = creditCardVerificationStatus.stream().map(ccVerificationStatus->{
            var creditCard = new CreditCard();
            BeanUtils.copyProperties(ccVerificationStatus,creditCard);
            if(ccVerificationStatus.getStatus().equals
                    (CreditCardVerificationStatus.VerificationStatus.APPROVED)){
               log.info("**** Credit card number will generated as application is approved : {}",
                       ccVerificationStatus.getRefId());
               var creditCardNumber = generateRandomInt(100000000000L, 999999999999L);
               creditCard.setCreditCardNumber(creditCardNumber);
               var cvv = generateRandomInt(100L, 999L);
               creditCard.setCvv(cvv);
            } else{
                log.info("**** Credit card number will not be generated as application is rejected : {}",
                        ccVerificationStatus.getRefId());
                creditCard.setCreditCardNumber(0L);
                creditCard.setCvv(0l);
            }

           creditCard.setStatus(ccVerificationStatus.getStatus().name());
            return creditCard;
        }).collect(Collectors.toList());

        log.info("***** Saving Credit Cards ****");
        creditCardRepo.saveAll(creditCards);
        log.info("***** Saved Credit Cards ****");
    }

    private Long generateRandomInt(Long min, Long max) {
         var randomDataGenerator = new RandomDataGenerator();
         return randomDataGenerator.nextLong(min,max);
    }

    public String getApplicationStatus(String applicationRefId) {
        log.info("*** Searching application status for reference id : {}", applicationRefId);
        var creditCard = creditCardRepo.findByRefId(applicationRefId);
        return creditCard.getStatus();
    }
}
