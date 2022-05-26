package com.example.dddshopping.order;

import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void 주문이_삭제_수정_되지않는_한_같은주문번호를가진다() {
        List<OrderLine> orderLines = List.of(new OrderLine(new Product(), 100, 1));
        ShippingInfo shippingInfo = new ShippingInfo();
        Order order = new Order(orderLines, shippingInfo);
        assertThat(order.equals(order)).isTrue();
    }

    @Test
    void 주문_다름_비교() {
        List<OrderLine> orderLines = List.of(new OrderLine(new Product(), 100, 1));
        ShippingInfo shippingInfo = new ShippingInfo();
        Order order = new Order(orderLines, shippingInfo);
        Order order1 = new Order(orderLines, shippingInfo);

        // order 과 order1 은 같은 주문으로 봐야하는 건가? 아니다. 주문 금액, 내용, 모두가 같더라도 엔티티는 엔티티 객체마다 고유하다. 따라서 order 과 order1 은 서로 다르다.
        assertThat(order).isNotEqualTo(order1);
    }

    @Test
    void 해시코드테스트(){
        List<OrderLine> orderLines = List.of(new OrderLine(new Product(), 100, 1));
        ShippingInfo shippingInfo = new ShippingInfo();
        Order order = new Order(orderLines, shippingInfo);
        Order order1 = new Order(orderLines, shippingInfo);

        System.out.println(order.hashCode());
        System.out.println(order1.hashCode());
    }
}