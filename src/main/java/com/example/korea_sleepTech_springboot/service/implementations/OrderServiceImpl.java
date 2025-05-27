package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.dto.order.OrderItemReqDto;
import com.example.korea_sleepTech_springboot.dto.order.OrderReqDto;
import com.example.korea_sleepTech_springboot.dto.order.OrderRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.entity.*;
import com.example.korea_sleepTech_springboot.repository.*;
import com.example.korea_sleepTech_springboot.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    // 주문 생성 시
    // 주문 정보가 orders, order_items 테이블에 저장
    // + 주문 기록이 order_logs 테이블에 자동 저장
    public ResponseDto<OrderRespDto> placeOrder(String userEmail, OrderReqDto requestDto) {
        OrderRespDto data = null;

        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("해당 Email의 사용자가 없습니다."));

        Order order = Order.builder()
                .user(user)
                .orderStatus("PENDING")
                .build();

        orderRepository.save(order);

        List<OrderItem> items = new ArrayList<>();
        List<OrderRespDto.OrderedItemInfo> responseItems = new ArrayList<>();

        for (OrderItemReqDto dto : requestDto.getItems()) {

            Product product = productRepository.findById(dto.getProductId()).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

            Stock stock = stockRepository.findByProductId(dto.getProductId());

            if (stock.getQuantity() < dto.getQuantity()) {
                throw new RuntimeException("재고가 부족하여 주문할 수 없습니다.");
            }

            stock.setQuantity(stock.getQuantity() - dto.getQuantity());

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(dto.getQuantity())
                    .build();

            items.add(item);

            responseItems.add(OrderRespDto.OrderedItemInfo.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .quantity(dto.getQuantity())
                            .price(product.getPrice())
                            .total(product.getPrice() * dto.getQuantity())
                    .build());
        }

        orderItemRepository.saveAll(items);

        data = OrderRespDto.builder()
                .orderId(order.getId())
                .message("주문 완료")
                .orderedItems(responseItems)
                .build();

        return ResponseDto.setSuccess("주문이 성공적으로 완료되었습니다.", data);
    }

    @Override
    public ResponseDto<List<OrderRespDto.OrderedItemInfo>> getOrderSummary(Long orderId) {
        List<Object[]> rows = orderRepository.getOrderSummary(orderId);
//        List<OrderSummaryProjection> rows = orderRepository.getOrderSummary(orderId);

        List<OrderRespDto.OrderedItemInfo> summary = new ArrayList<>();

        for (Object[] row : rows) {
            summary.add(OrderRespDto.OrderedItemInfo.builder()
                    // Object 객체 타입은 반드시 참조타입만 호환 가능
                    // Object >> Number >> long 타입
                            .productId(((Number)row[0]).longValue())
                            .productName((String)row[2])
                            .quantity(((Number)row[3]).intValue())
                            .price(((Number)row[4]).intValue())
                            .total(((Number)row[5]).intValue())
                    .build());
        }

//        List<OrderResponseDto.OrderedItemInfo> summary = rows.stream().map(row ->
//        OrderResponseDto.OrderedItemInfo.builder()
//            .productId(row.getOrderId()) // 또는 null 넣어도 됨
//            .productName(row.getProductName())
//            .quantity(row.getQuantity())
//            .price(row.getPrice())
//            .total(row.getTotalPrice())
//            .build()
//          ).toList();

        return ResponseDto.setSuccess("주문 요약 조회 성공", summary);
    }
}


















