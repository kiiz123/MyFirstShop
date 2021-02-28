package pl.sdcacademy.myfirstshop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class Order {
    private OrderItem[] orderItems;
    private BigDecimal totalOrderItemPrice;
    private LocalDate itemAddedLocalDate;
    private Customer customer;
    private Integer itemsIndex;
    private boolean orderClosed;
    private UUID uniqueOrderId;


    private Order(Customer customer) {
        this.orderItems = new OrderItem[ShopConstants.MAX_ITEMS_IN_ORDER];
        this.customer = customer;
        this.itemAddedLocalDate = LocalDate.now();
        this.itemsIndex=-1;
        this.orderClosed = false;
        this.uniqueOrderId=UUID.randomUUID();
        this.totalOrderItemPrice=BigDecimal.valueOf(0.0);
    }

    public static Order createEmptyOrder(Customer customer){
        Order order = new Order(customer);
        return order;
    }

    public static Order createOrderWithItems(Customer customer,OrderItem[] orderItems){
        Order order = createEmptyOrder(customer);
        if (order.addOrderItems(orderItems)) {
            return order;
        }
        else {
            return null;
        }
    }

    public boolean addOrderItems(OrderItem ... orderItems) {
        Integer numberOfAddedOrderItems = orderItems.length;
        if (orderClosed) {
            return false;
        }
        else if (AvailableFreeItems() < numberOfAddedOrderItems ) {
            if ( isMaxItemsInOrder() ) {
                this.closeOrder();
            }
            return false; //za dużo w zamówieniu
        }
        else {
            int n = numberOfAddedOrderItems;
            System.arraycopy(orderItems,0,this.orderItems,itemsIndex+1,n);
            setItemsIndex(itemsIndex + n);
            setTotalOrderItemPrice(itemsIndex);
            return true;
        }
    }

    public boolean isMaxItemsInOrder() {
        return (this.getNumberOfItems() == ShopConstants.MAX_ITEMS_IN_ORDER);
    }
    public Integer AvailableFreeItems () {
        return ShopConstants.MAX_ITEMS_IN_ORDER - this.getNumberOfItems();
    }
    public Integer getNumberOfItems() {
        return itemsIndex+1;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isOrderEmpty() {
        return (itemsIndex == -1 );
    }

    public void closeOrder() {
        orderClosed = true;
    }

    private BigDecimal calcTotalOrderPrice(int itemsIndex) {
        BigDecimal TotalOrderPrice = BigDecimal.valueOf(0);
        for (int i=0;i<=itemsIndex;i++)
        {
            TotalOrderPrice=TotalOrderPrice.add(this.orderItems[i].getTotalOrderItemPrice());
        }
        return TotalOrderPrice;
    }

    public boolean containsProduct( String pattern )
    {
        for (int i=0;i<=itemsIndex;i++) {
            if ( orderItems[i].containsProduct(pattern) ) {
                return true;
            }
        }
        return false;
    }

    public void setTotalOrderItemPrice(int itemsIndex) {
        this.totalOrderItemPrice = calcTotalOrderPrice(itemsIndex);
    }

    public void setItemsIndex(Integer itemsIndex) {
        this.itemsIndex = itemsIndex;
    }

    public BigDecimal getTotalOrderItemPrice() {
        return totalOrderItemPrice;
    }

    public UUID getUniqueOrderId() {
        return uniqueOrderId;
    }

    public boolean isByCustomer(String pattern) {
        return customer.matches(pattern);
    }

    @Override
    public String toString() {
        StringBuilder orderString = new StringBuilder("Zamówienie "+uniqueOrderId+" ( "+itemAddedLocalDate+" ) dla firmy " + customer + "\n");

        if (itemsIndex>-1) {
            for (int i = 0; i <= this.itemsIndex; i++) {
                orderString.append("\t"+(i + 1) + " " + this.orderItems[i].toString() + "\n");
            }
            orderString.append("Cena zamówienia: " + this.getTotalOrderItemPrice().toString() + "\n");
        }
        else {
            orderString.append("Puste zamówienie");
        }

        return orderString.toString();
    }


}
