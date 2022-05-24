package com.example.dddshopping.order;

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
    SHIPPED, DELIVERING, DELIVERY_COMPLETED;
    public boolean isShippingChangeable(){
        return false;
    }

}
