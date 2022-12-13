package com.brainstem.myestate.service;

import com.brainstem.myestate.payload.OrderResponse;
import com.brainstem.myestate.payload.OrderDto;

public interface OrderService {
    OrderResponse placeOrder(OrderDto orderDto);
}
