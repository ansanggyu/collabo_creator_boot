package org.example.collabo_creator_boot.order.domain;

public enum OrderStatus {
    PENDING("결제 대기"),
    COMPLETED("결제 완료"),
    CANCELED("주문 취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromDbValue(String value) {
        try {
            int ordinal = Integer.parseInt(value);
            return OrderStatus.values()[ordinal];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid OrderStatus value: " + value);
        }
    }
}
