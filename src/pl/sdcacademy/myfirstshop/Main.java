package pl.sdcacademy.myfirstshop;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("babeczka śmietankowa", BigDecimal.valueOf(10.02));
        Product product2 = new Product("muffin czekoladowy", BigDecimal.valueOf(8.30));
        Product product3 = new Product("pączek malinowy", BigDecimal.valueOf(3.00));
        Product product4 = new Product("rogal z makiem", BigDecimal.valueOf(4.75));
        Product product5 = new Product("babeczka z kajmakiem", BigDecimal.valueOf(9.00));
        Product product6 = new Product("napoleonka", BigDecimal.valueOf(7.00));
        Product product7 = new Product("eklerka", BigDecimal.valueOf(10.00));
        Product product8 = new Product("beza truskawkowa", BigDecimal.valueOf(8.46));
        Product product9 = new Product("beza cytrynowa", BigDecimal.valueOf(6.90));
        Product product10 = new Product("bardzo wypasione ciastko", BigDecimal.valueOf(40.00));

        Customer customer1 = new Customer("Smakołyki u Ady","ul. Czekoladowa 12/5 Lilków","4859023785982");
        Customer customer2 = new Customer("Pan Czesio","ul. Krótka 12 Lilków","5555555555555");
        Customer customer3 = new Customer("Kawiarnia na patyku","ul. Pobożna 99 Lilków","888888");
        Customer customer4 = new Customer("Przysmaki u babci Jadzi","ul. Neutralna 8 Lilków","4564456464564");


        OrderItem orderItem1 = new OrderItem(product1,13);
        OrderItem orderItem2 = new OrderItem(product2,2);
        OrderItem orderItem3 = new OrderItem(product3,10);
        OrderItem orderItem4 = new OrderItem(product4,70);
        OrderItem orderItem5 = new OrderItem(product1,100);
        OrderItem orderItem6 = new OrderItem(product9,1);
        OrderItem orderItem7 = new OrderItem(product10,200);
        OrderItem orderItem8 = new OrderItem(product7,3);
        OrderItem orderItem9 = new OrderItem(product6,15);
        OrderItem orderItem10 = new OrderItem(product8,64);


//        Order order1 = Order.createOrderWithItems(customer1,new OrderItem[]{orderItem1,orderItem2});
//        System.out.println(order1);
//        Order order2 = Order.createOrderWithItems(customer2,new OrderItem[]{orderItem3,orderItem4});
//        System.out.println(order2);


        Shop shop = new Shop("DROP SHOP");
        if ( shop.availableFreeOrders() >= 3) {
             shop.addOrder(customer1,orderItem1,orderItem2,orderItem3);
        }
        else {
            System.out.println("Za dużo zamówień");
        }
        if ( shop.availableFreeOrders() >= 2) {
            shop.addOrder(customer2, orderItem4, orderItem5);
        }
        else {
            System.out.println("Za dużo zamówień");
        }
        if ( shop.availableFreeOrders() >= 1) {
            shop.addOrder(customer3, orderItem6);
        }
        else {
            System.out.println("Za dużo zamówień");
        }
        if ( shop.availableFreeOrders() >= 3) {
            shop.addOrder(customer4, orderItem7, orderItem8, orderItem9);
        }
        else {
            System.out.println("Za dużo zamówień");
        }
        if ( shop.availableFreeOrders() >= 3) {
            shop.addOrder(customer1, orderItem1, orderItem10, orderItem3);
        }
        else {
            System.out.println("Za dużo zamówień");
        }

        System.out.println(shop);

        System.out.println("Wszystkie zamówienia sklepu "+shop.getName()+" to kwota "+shop.getAllOrdersTotalPrice());
        System.out.println("--------------------------SZUKANIE zamówień dla Firmy *bab***-----------------------------------");
        Order[] ordersbyCustomer=shop.searchByCustomer("bab");
        for (int i=0;i<ordersbyCustomer.length;i++) {
            System.out.println(ordersbyCustomer[i]);
        }

        System.out.println("--------------------------SZUKANIE zamówień z produktem *beza*-----------------------------------");
        Order[] ordersWithProducts = shop.searchByProdut("beza");
        for (int i=0;i<ordersWithProducts.length;i++) {
           System.out.println(ordersWithProducts[i]);
        }
    }
}
