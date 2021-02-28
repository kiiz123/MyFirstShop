package pl.sdcacademy.myfirstshop;

import java.util.Locale;

public class Customer {
    private final String name ;
    private final String address ;
    private String telNumber;


    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Customer(String name, String address, String telNumber) {
        this.name = name;
        this.address = address;
        this.telNumber = telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
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
        StringBuilder retString=new StringBuilder(name+", Adres: "+address);
        if (telNumber !=null) {
            retString.append(", Numer telefonu: "+telNumber);
        }
        return retString.toString();
    }
}
