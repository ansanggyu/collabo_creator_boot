package org.example.collabo_creator_boot.order.controller;

import lombok.RequiredArgsConstructor;
import org.example.collabo_creator_boot.common.PageRequestDTO;
import org.example.collabo_creator_boot.common.PageResponseDTO;
import org.example.collabo_creator_boot.order.domain.OrderStatus;
import org.example.collabo_creator_boot.order.dto.OrderDetailDTO;
import org.example.collabo_creator_boot.order.dto.OrderListDTO;
import org.example.collabo_creator_boot.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<OrderListDTO>> getOrderList(
            @RequestParam("creatorId") String creatorId,
            @ModelAttribute PageRequestDTO pageRequestDTO) {

        PageResponseDTO<OrderListDTO> response = orderService.getOrderListByCreator(creatorId, pageRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<OrderDetailDTO> getOrderDetail(
            @RequestParam("creatorId") String creatorId,
            @PathVariable Long orderNo) {

        OrderDetailDTO orderDetail = orderService.getOrderDetailByCreator(creatorId, orderNo);
        return ResponseEntity.ok(orderDetail);
    }

    @PatchMapping("/{orderNo}/status")
    public ResponseEntity<Void> updateOrderStatus(
            @PathVariable Long orderNo,
            @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(orderNo, status);
        return ResponseEntity.ok().build();
    }
}
