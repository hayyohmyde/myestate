package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.exception.CustomErrorResponse;
import com.brainstem.myestate.exception.GlobalExceptionHandler;
import com.brainstem.myestate.exception.PaymentException;
import com.brainstem.myestate.model.Order;
import com.brainstem.myestate.model.Payment;
import com.brainstem.myestate.payload.OrderResponse;
import com.brainstem.myestate.payload.OrderDto;
import com.brainstem.myestate.repository.OrderRepository;
import com.brainstem.myestate.repository.PaymentRepository;
import com.brainstem.myestate.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public OrderResponse placeOrder(OrderDto orderDto) {
        Order order = orderDto.getOrder();
        order.setStatus("IN_PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderDto.getPayment();

        if(!payment.getCardType().equals("DEBIT")){
            throw new PaymentException("Card type is not supported");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESSFUL");
        return orderResponse;
    }
}
