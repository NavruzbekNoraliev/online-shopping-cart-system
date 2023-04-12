package com.example.payment.paymentservice.service;

import com.example.payment.paymentservice.model.TransactionStatus;
import com.example.payment.paymentservice.model.dto.PaymentDto;
import com.example.payment.paymentservice.model.entity.PaymentEntity;
import com.example.payment.paymentservice.model.mapper.PaymentMapper;
import com.example.payment.paymentservice.repository.PaymentRepository;
import com.example.payment.paymentservice.rest.request.PaymentRequest;
import com.example.payment.paymentservice.rest.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    private PaymentMapper paymentMapper = new PaymentMapper();

    public PaymentResponse utilPayment(PaymentRequest paymentRequest) {
        log.info("Utility payment processing {}", paymentRequest.toString());

        PaymentEntity entity = new PaymentEntity();
        BeanUtils.copyProperties(paymentRequest, entity);
        entity.setStatus(TransactionStatus.PROCESSING);
        PaymentEntity optUtilPayment = paymentRepository.save(entity);

        String transactionId = UUID.randomUUID().toString();
        optUtilPayment.setStatus(TransactionStatus.SUCCESS);
        optUtilPayment.setTransactionId(transactionId);

        paymentRepository.save(optUtilPayment);

        return PaymentResponse.builder().message("Utility Payment Successfully Processed").transactionId(transactionId).build();
    }

    public List<PaymentDto> readPayments(Pageable pageable) {
        Page<PaymentEntity> allUtilPayments = paymentRepository.findAll(pageable);
        return paymentMapper.convertToDtoList(allUtilPayments.getContent());
    }
}