package com.example.dddshopping.order;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class OrderTest {

    @Test
    void 최소_한_종류이상의_상품을_주문해야한다(){
        List<OrderLine> orderLines = List.of();
        ShippingInfo shippingInfo = new ShippingInfo();
        assertThatThrownBy(() -> new Order(orderLines,shippingInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("적어도 하나의 상품이 있어야 합니다.");
    }
}