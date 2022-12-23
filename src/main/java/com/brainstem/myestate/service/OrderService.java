package com.brainstem.myestate.service;

import com.brainstem.myestate.dto.response.OrderResponse;
import com.brainstem.myestate.dto.request.OrderDto;

public interface OrderService {
    OrderResponse placeOrder(OrderDto orderDto);
}
