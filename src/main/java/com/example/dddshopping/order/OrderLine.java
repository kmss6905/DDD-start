package com.example.dddshopping.order;

public class OrderLine {
    private Product product; // <-- price 는 여기에 있는 것이 맞지 않을까?
    private int price;
    private int quantity;
    private int amounts;

    public OrderLine(Product product, int price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    public int getAmounts() {
        return amounts;
    }

    public int calculateAmounts(){
        return price * quantity;
    }

    public void verifyQuantityAtLeastOneAndHasProduct(){
        if(product == null || quantity == 0){
            throw new IllegalArgumentException("상품은 한 개 이상 주문할 수 있다.");
        }
    }
}
