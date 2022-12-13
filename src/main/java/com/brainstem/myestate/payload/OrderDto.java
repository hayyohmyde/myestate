package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.Order;
import com.brainstem.myestate.model.Payment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDto {
    private Order order;
    private Payment payment;
}
