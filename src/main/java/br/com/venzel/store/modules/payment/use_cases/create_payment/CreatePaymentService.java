package br.com.venzel.store.modules.payment.use_cases.create_payment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.store.modules.payment.dtos.PaymentDTO;
import br.com.venzel.store.modules.payment.entities.Payment;
import br.com.venzel.store.modules.payment.exceptions.PaymentAlreadyExistsException;
import br.com.venzel.store.modules.payment.mapper.PaymentMapper;
import br.com.venzel.store.modules.payment.repositories.PaymentRepository;
import br.com.venzel.store.modules.payment.utils.PaymentMessageUtils;

@Service
public class CreatePaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Transactional
    public PaymentDTO execute(PaymentDTO dto) {
        
        Optional<Payment> optionalEntity = paymentRepository.findById(dto.getId());

        if (optionalEntity.isPresent()) {
            throw new PaymentAlreadyExistsException(PaymentMessageUtils.PAYMENT_ALREADY_EXISTS);
        }

        Payment payment = paymentMapper.toEntity(dto);

        paymentRepository.save(payment);

        return paymentMapper.toDTO(payment);
    }
}
