package pl.sdcacademy.myfirstshop;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OrderItem {
    private final Product product;
    private final BigDecimal totalOrderItemPrice;
    private final Integer quantity;

    public OrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        totalOrderItemPrice=this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTotalOrderItemPrice() {
        return totalOrderItemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public boolean containsProduct(String pattern) {
        return product.matches(pattern);
    }

    @Override
    public String toString() {
        return "pozycja zamówienia:\n" +product+", sztuk: "+quantity+", cena pozycji: "+totalOrderItemPrice;
    }
}
