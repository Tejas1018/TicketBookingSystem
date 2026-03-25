package com.paymentService.PaymentService.service;


import com.commonUtilities.CommonUtilities.events.SeatReservedEvent;
import com.paymentService.PaymentService.exceptions.PaymentFailureException;
import com.paymentService.PaymentService.exceptions.PaymentServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private KafkaTemplate<String,Object> kafkaTemplate;
    public void processEvent(SeatReservedEvent event){

        try{
            log.info("Processing SeatReservedEvent for bookingId{}",event.bookingId());
            if(event.amount() > 2000) {
                log.info("Payment amount exceeded for bookingId{}",event.bookingId());
                throw new PaymentFailureException("❌Payment FailedFailed❌");
            }
            else{
            log.info("✅Payment successfull for bookingId{}",event.bookingId());
            }
        } catch (Exception e) {
            log.error("❌ Payment failed for bookingId: {}. Reason: {}", event.bookingId(), e.getMessage());
            throw new PaymentServiceException("Payment processing failed for bookingId: " + event.bookingId());
        }
    }
}
