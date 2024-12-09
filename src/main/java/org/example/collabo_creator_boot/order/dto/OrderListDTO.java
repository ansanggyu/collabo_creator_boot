package org.example.collabo_creator_boot.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.collabo_creator_boot.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO {
    private Long orderNo;
    private LocalDateTime orderDate;
    private String customerName;
    private String customerPhone;
    private String customerAddr;
    private String customerAddrDetail;
    private List<OrderItemDTO> orderItems;
    private Integer totalPrice;
    private OrderStatus orderStatus;

    @JsonProperty("orderDate")
    public String getFormattedOrderDate() {
        if (orderDate != null) {
            return orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

    @JsonProperty("orderStatus")
    public String getOrderStatusDescription() {
        return orderStatus != null ? orderStatus.name() : "UNKNOWN";
    }
}
