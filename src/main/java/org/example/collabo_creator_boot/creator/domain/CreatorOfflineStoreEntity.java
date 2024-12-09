package org.example.collabo_creator_boot.creator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.collabo_creator_boot.common.BasicEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "creator_offline_store")
public class CreatorOfflineStoreEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_no", nullable = false)
    private Long storeNo;

    @Column(name = "store_image", length = 2000)
    private String storeImage;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_address")
    private String storeAddress;

    @Column(name = "store_address_detail")
    private String storeAddressDetail;

    @Column(name = "latitude", precision = 10, scale = 8, nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8, nullable = false)
    private BigDecimal longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id",referencedColumnName="creator_id")
    private CreatorEntity creatorEntity;
}
