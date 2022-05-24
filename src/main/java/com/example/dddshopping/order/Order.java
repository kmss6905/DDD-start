package com.example.dddshopping.order;

public class Order {
    private OrderState state;
    private ShippingInfo shoppingInfo;
    private PaymentInfo paymentInfo;

    public void changeShipping(ShippingInfo newShipping){
        if(!state.isShippingChangeable()){
            throw new IllegalArgumentException("can't change shipping in " + state);
        }
        this.shoppingInfo = newShipping;
    }
}
