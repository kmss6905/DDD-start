package com.example.dddshopping.order;

/**
 * 도메인 : 소프트웨어로 해결하고자 하는 문제 영역
 * 주문 대기중(PAYMENT_WAITING) 상태와 상품 준비 중(PREPARING) 상태의 메서드는 true를 리턴함
 * 즉, OrderState 는 주문 대기 중이거나 상품 준비 중에는 배송지를 변경할 수 있다는 도메인 규칙을 구현하고 있음
 */
public enum OrderState {
    PAYMENT_WAITING{
        public boolean isShippingChangeable(){
            return true;
        }
    },
    PREPARING{
        public boolean isShippingChangeable(){
            return false;
        }
    },
    SHIPPED, DELIVERING, DELIVERY_COMPLETED, CANCELED;
    public boolean isShippingChangeable(){
        return false;
    }

}
