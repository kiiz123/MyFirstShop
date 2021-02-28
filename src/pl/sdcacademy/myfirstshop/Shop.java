package pl.sdcacademy.myfirstshop;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

public class Shop {
    private Order[] orders ;
    private String name;
    private Integer orderIndex;


    public Shop(String ShopName) {
        name = ShopName;
        orderIndex=-1;
        orders = new Order[ShopConstants.MAX_ORDERS];
    }

    public Order addOrder(Customer customer, OrderItem ... items) {
        Integer ordersToAdd = items.length;
        if ( ordersToAdd> availableFreeOrders()) {
            return null;
        }
        else {
            Order order = Order.createEmptyOrder(customer);
            order.addOrderItems(items);
            this.orders[orderIndex+1]=order;
            orderIndex = orderIndex +1;
            return order;
        }
    }

    public Integer getNumberOfOrders() {
        return orderIndex+1;
    }

    public Integer availableFreeOrders() {
        return ShopConstants.MAX_ORDERS - this.getNumberOfOrders();
    }

    public BigDecimal getAllOrdersTotalPrice(){
        BigDecimal sumPrice = BigDecimal.valueOf(0.0);
        for (int i=0;i<=this.orderIndex;i++) {
            sumPrice=sumPrice.add(orders[i].getTotalOrderItemPrice());
        }
        return sumPrice;
    }

    public String getName() {
        return name;
    }
    public Order[] searchByCustomer(String custPattern) {
         return search(custPattern,ShopConstants.SEARCH_BY_CUSTOMER);
    }

    public Order[] searchByProdut(String prodPattern) {
        return search(prodPattern,ShopConstants.SEARCH_BY_PRODUCT);
    }

    private Order[] search(String pattern, short patternType)
    {
        Order[] retOrders = new Order[orderIndex+1];
        int count=-1;

        for (int i=0;i<=this.orderIndex;i++) {
            boolean check = false;
            switch ( patternType )
            {
                case ShopConstants.SEARCH_BY_CUSTOMER :
                    check = this.orders[i].isByCustomer(pattern);
                    break;
                case ShopConstants.SEARCH_BY_PRODUCT :
                    check = this.orders[i].containsProduct(pattern);
                    break;
                default:
                    check = false;
            }
            if ( check)
            {
                count++;
                retOrders[count]=this.orders[i];
            }
        }
        Order copiedOrders[] = Arrays.copyOf(retOrders,count+1);
        return copiedOrders;
    }

    @Override
    public String toString() {
       StringBuilder retString = new StringBuilder("Lista zamówień dla sklepu "+getName()+"\n");
        for (int i=0;i<=this.orderIndex;i++) {
            retString.append((i+1)+" "+orders[i].toString());
        }
        return retString.toString();
    }

}
