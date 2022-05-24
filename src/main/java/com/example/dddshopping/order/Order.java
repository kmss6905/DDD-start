package com.example.dddshopping.order;

// 주문 도메인
public class Order {
    private OrderState state;
    private ShippingInfo shoppingInfo;
    private PaymentInfo paymentInfo;

    // Order -> ShippingInfo
    public void changeShipping(ShippingInfo newShipping){
        if(!state.isShippingChangeable()){
            throw new IllegalArgumentException("can't change shipping in " + state);
        }
        this.shoppingInfo = newShipping;
    }

    // Order -> OrderState
    private boolean isShippingChangeable(){
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }


}
