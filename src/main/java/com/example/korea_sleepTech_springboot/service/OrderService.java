package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.order.OrderReqDto;
import com.example.korea_sleepTech_springboot.dto.order.OrderRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderService {

    ResponseDto<OrderRespDto> placeOrder(String userEmail, @Valid OrderReqDto dto);

    ResponseDto<List<OrderRespDto.OrderedItemInfo>> getOrderSummary(Long orderId);
}
