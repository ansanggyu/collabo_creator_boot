package org.example.collabo_creator_boot.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.collabo_creator_boot.common.BasicEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_image")
public class ProductImageEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_no")
    private Integer productImageNo;

    @Column(name = "product_image_url", length = 2000)
    private String productImageUrl;

    @Column(name = "product_image_ord")
    private Integer productImageOrd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no", referencedColumnName = "product_no")
    private ProductEntity productEntity;

    public void linkToProduct(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

}