package helper.animal_hierarchy;


import helper.set.CustomerWithAddress;
import core.logger.MyLogger;

public class Animal {
    private boolean vegetarian;
    private String eats;
    private int noOfLegs;
    private CustomerWithAddress customer;

    public Animal(boolean veg , String food, int legs) {
        MyLogger.logMethodEntry("initAnimal", this, new Object[] { veg, food, legs },
                new Class<?>[] { boolean.class, String.class, int.class });
        this.vegetarian = veg;
        this.eats = food;
        this.noOfLegs = legs;
        MyLogger.logMethodExit("initAnimal", this, void.class, null, new Object[] { veg, food, legs },
                new Class<?>[] { boolean.class, String.class, int.class });
    }

    public boolean isVegetarian() {
        MyLogger.logMethodEntry("isVegetarian", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("isVegetarian", this, boolean.class, vegetarian, (Object[]) null, new Class<?>[0]);
        return vegetarian;
    }

    public void setVegetarian(boolean veg) {
        MyLogger.logMethodEntry("setVegetarian", this, (new Object[] { veg }), new Class<?>[] { boolean.class });
        this.vegetarian = veg;
        MyLogger.logMethodExit("setVegetarian", this, void.class, null, (new Object[] { veg }),
                new Class<?>[] { boolean.class });
    }

    public String getEats() {
        MyLogger.logMethodEntry("getEats", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("getEats", this, String.class, eats, (Object[]) null, new Class<?>[0]);
        return eats;
    }

    public void setEats(String food) {
        MyLogger.logMethodEntry("setEats", this, (new Object[] { food }), new Class<?>[] { String.class });
        this.eats = food;
        MyLogger.logMethodExit("setEats", this, void.class, null, (new Object[] { food }),
                new Class<?>[] { String.class });
    }

    public int getNoOfLegs() {
        MyLogger.logMethodEntry("getNoOfLegs", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("getNoOfLegs", this, int.class, noOfLegs, (Object[]) null, new Class<?>[0]);
        return noOfLegs;
    }

    public void setNoOfLegs(int legs) {
        MyLogger.logMethodEntry("setNoOfLegs", this, (new Object[] { legs }), new Class<?>[] { int.class });
        this.noOfLegs = legs;
        MyLogger.logMethodExit("setNoOfLegs", this, void.class, null, (new Object[] { legs }),
                new Class<?>[] { int.class });
    }

}
