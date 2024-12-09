package org.example.collabo_creator_boot.order.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.example.collabo_creator_boot.common.PageRequestDTO;
import org.example.collabo_creator_boot.common.PageResponseDTO;
import org.example.collabo_creator_boot.customer.domain.QCustomerEntity;
import org.example.collabo_creator_boot.order.domain.OrdersEntity;
import org.example.collabo_creator_boot.order.domain.QOrderItemEntity;
import org.example.collabo_creator_boot.order.domain.QOrdersEntity;
import org.example.collabo_creator_boot.order.dto.OrderItemDTO;
import org.example.collabo_creator_boot.order.dto.OrderListDTO;
import org.example.collabo_creator_boot.order.repository.OrderRepository;
import org.example.collabo_creator_boot.product.domain.QProductEntity;
import org.example.collabo_creator_boot.product.domain.QProductImageEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class OrderSearchImpl extends QuerydslRepositorySupport implements OrderSearch {

    private final OrderRepository orderRepository;

    public OrderSearchImpl(OrderRepository orderRepository) {
        super(OrdersEntity.class);
        this.orderRepository = orderRepository;
    }

    @Override
    public PageResponseDTO<OrderListDTO> orderListByCreator(String creatorId, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("orderNo").descending());

        QOrdersEntity orders = QOrdersEntity.ordersEntity;
        QCustomerEntity customer = QCustomerEntity.customerEntity;

        // 기본 주문 데이터 조회
        JPQLQuery<OrderListDTO> query = from(orders)
                .leftJoin(orders.customerEntity, customer)
                .where(orders.creatorEntity.creatorId.eq(creatorId))
                .select(Projections.bean(OrderListDTO.class,
                        orders.orderNo,
                        orders.createdAt.as("orderDate"),
                        customer.customerName,
                        customer.customerPhone,
                        customer.customerAddr,
                        customer.customerAddrDetail,
                        orders.totalPrice,
                        orders.status.as("orderStatus")
                ));

        this.getQuerydsl().applyPagination(pageable, query);

        List<OrderListDTO> dtoList = query.fetch();

        // 주문 아이템 데이터 추가
        dtoList.forEach(dto -> {
            List<OrderItemDTO> orderItems = orderRepository.findOrderItems(dto.getOrderNo());
            dto.setOrderItems(orderItems);
        });

        long total = query.fetchCount();

        return PageResponseDTO.<OrderListDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }
}
