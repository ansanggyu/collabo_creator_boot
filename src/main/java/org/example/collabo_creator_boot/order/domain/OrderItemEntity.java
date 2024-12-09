package org.example.collabo_creator_boot.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.collabo_creator_boot.common.BasicEntity;
import org.example.collabo_creator_boot.product.domain.ProductEntity;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItemEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_no", nullable = false)
    private Long orderItemNo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_no", referencedColumnName = "order_no", nullable = false)
    private OrdersEntity ordersEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no", referencedColumnName = "product_no", nullable = false)
    private ProductEntity productEntity;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private int unitPrice;
}
