package com.example.project.helper.set;

import com.example.project.logic.logger.MyLogger;

public class CustomerWithAddress extends Customer {

    private String address;

    public CustomerWithAddress(int id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public String getAddress() {
        MyLogger.logMethodEntry("getAddress", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("getAddress", this, String.class, address, (Object[]) null, new Class<?>[0]);
        return address;
    }

    public void setAddress(String address) {
        MyLogger.logMethodEntry("setAddress", this, new Object[] { address }, new Class<?>[] { String.class });
        this.address = address;
        MyLogger.logMethodExit("setAddress", this, void.class, null, new Object[] { address },
                        new Class<?>[] { String.class });
    }

    // Additional methods or overrides can be added here
}
