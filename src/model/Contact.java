package model;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Contact implements Serializable {
    private String name;
    private String phoneNumber;

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\+?\\d{10,13}$");


    public Contact(String name, String phoneNumber) {
        if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber;
    }
}
