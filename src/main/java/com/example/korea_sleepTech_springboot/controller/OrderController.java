package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.constants.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.order.OrderReqDto;
import com.example.korea_sleepTech_springboot.dto.order.OrderRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.ORDER_API)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private static final String PLACE_ORDER = "/place";
    private static final String GET_SUMMARY = "/summary/{orderId}";

    @PostMapping(PLACE_ORDER)
    public ResponseEntity<ResponseDto<OrderRespDto>> placeOrder(@AuthenticationPrincipal String userEmail, @Valid @RequestBody OrderReqDto dto) {
        ResponseDto<OrderRespDto> response = orderService.placeOrder(userEmail, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(GET_SUMMARY)
    public ResponseEntity<ResponseDto<List<OrderRespDto.OrderedItemInfo>>> getOrderSummary(@PathVariable Long orderId) {
        ResponseDto<List<OrderRespDto.OrderedItemInfo>> response = orderService.getOrderSummary(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }



}
