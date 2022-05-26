package com.example.dddshopping.order;

import java.util.List;

public class Order {
    private String orderNumber;

    private List<OrderLine> orderLines;
    private int totalAmount;

    private OrderState state;
    private ShippingInfo shippingInfo;
    private PaymentInfo paymentInfo;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo){
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(obj.getClass() != Order.class) return false;
        Order other = (Order) obj;
        if(this.orderNumber == null) return false;
        return this.orderNumber.equals(other.orderNumber);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderNumber == null) ? 0: orderNumber.hashCode());
        return result;
    }


    private void setShippingInfo(ShippingInfo shippingInfo) {
        // ShippinfIngo 가 null 이면 익셉션이 발생하는 데 이렇게 함으로써 '배송지 정보 필수'라는 도메인 규칙을 구현한다.
        if(shippingInfo == null)
            throw new IllegalArgumentException("no ShippingInfo");
        this.shippingInfo = shippingInfo;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void calculateTotalAmounts() {
        this.totalAmount = new Money(orderLines.stream()
                .mapToInt(OrderLine::getAmounts).sum()).getValue();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if(orderLines == null || orderLines.isEmpty()){
            throw new IllegalArgumentException("적어도 하나의 상품이 있어야 합니다.");
        }
    }

    // Order -> ShippingInfo
    // 배송지 변경이나 주문 취소 기능은 출고 전에만 가능하다
    public void changeShipping(ShippingInfo newShipping){
        verifyNotYetShipped();
        this.shippingInfo = newShipping;
    }

    public void cancel(){
        // 출고 전에 주문을 취소할 수 있다.( = 출고 이후에는 주문을 취소 할 수 없다. )
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    // "출고 전" 이라고 그대로의 용어를 담지 않았다. 여기서는 선적, 아지 배송되지 않았다는 의미의 shipped 를 담고있다.
    private void verifyNotYetShipped() {
        if(state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING)
            throw new IllegalArgumentException("already shipped");
    }

}
