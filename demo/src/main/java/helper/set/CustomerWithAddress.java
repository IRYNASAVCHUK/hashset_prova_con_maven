package helper.set;

import core.logger.MyLogger;

public class CustomerWithAddress extends Customer {
    private String address;
    private Customer newCustomer = new Customer(99, "Ivan");

    public CustomerWithAddress(int id, String name, String address) {
        super(id, name);
        MyLogger.logMethodEntry("initCustomerWA", this, new Object[] { id, name, address },
                new Class<?>[] { int.class, String.class, String.class });
        this.address = address;
        MyLogger.logMethodExit("initCustomerWA", this, void.class, null, new Object[] { id, name, address },
                new Class<?>[] { int.class, String.class, String.class });
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
}
