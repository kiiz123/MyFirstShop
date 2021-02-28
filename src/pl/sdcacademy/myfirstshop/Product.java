package pl.sdcacademy.myfirstshop;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;


    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean matches(String pattern) {
        if (pattern.length()<3) {
            return false;
        }
        else {
            String str1 = name.toUpperCase();
            String str2 = pattern.toUpperCase();
            return (str1.contains(str2));
        }
    }

    @Override
    public String toString() {
        return "\t\tprodukt: "+ name +", cena jednost.: " + price;
    }
}
