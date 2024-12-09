package org.example.collabo_creator_boot.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.collabo_creator_boot.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class OrderDetailDTO {
    private Long orderNo;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    // 주문자 정보
    private String customerName;
    private String customerPhone;
    private String customerAddr;
    private String customerAddrDetail;

    // 주문 상품
    private List<OrderItemDTO> orderItems;

    public OrderDetailDTO(Long orderNo, LocalDateTime orderDate, OrderStatus orderStatus,
                          String customerName, String customerPhone, String customerAddr, String customerAddrDetail) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddr = customerAddr;
        this.customerAddrDetail = customerAddrDetail;
    }

    @JsonProperty("orderDate")
    public String getFormattedOrderDate() {
        if (orderDate != null) {
            return orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

    @JsonProperty("orderStatusDescription")
    public String getOrderStatusDescription() {
        return orderStatus != null ? orderStatus.getDescription() : "알 수 없음";
    }
}
