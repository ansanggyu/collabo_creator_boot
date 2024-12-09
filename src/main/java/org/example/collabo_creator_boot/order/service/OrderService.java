package org.example.collabo_creator_boot.order.service;

import lombok.RequiredArgsConstructor;
import org.example.collabo_creator_boot.common.PageRequestDTO;
import org.example.collabo_creator_boot.common.PageResponseDTO;
import org.example.collabo_creator_boot.order.domain.OrderStatus;
import org.example.collabo_creator_boot.order.domain.OrdersEntity;
import org.example.collabo_creator_boot.order.dto.OrderDetailDTO;
import org.example.collabo_creator_boot.order.dto.OrderItemDTO;
import org.example.collabo_creator_boot.order.dto.OrderListDTO;
import org.example.collabo_creator_boot.order.repository.OrderRepository;
import org.example.collabo_creator_boot.order.repository.search.OrderSearch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderSearch orderSearch;
    private final OrderRepository orderRepository;

    public PageResponseDTO<OrderListDTO> getOrderListByCreator(String creatorId, PageRequestDTO pageRequestDTO) {
        return orderSearch.orderListByCreator(creatorId, pageRequestDTO);
    }

    public OrderDetailDTO getOrderDetailByCreator(String creatorId, Long orderNo) {
        OrderDetailDTO orderDetail = orderRepository.findOrderDetailByCreator(creatorId, orderNo);
        if (orderDetail == null) {
            throw new IllegalArgumentException("Order not found for creatorId: " + creatorId);
        }
        List<OrderItemDTO> orderItems = orderRepository.findOrderItems(orderNo);
        orderDetail.setOrderItems(orderItems);
        return orderDetail;
    }

    public void updateOrderStatus(Long orderNo, OrderStatus newStatus) {
        OrdersEntity order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderNo));
        order.setStatus(newStatus);
        orderRepository.save(order);
    }
}
