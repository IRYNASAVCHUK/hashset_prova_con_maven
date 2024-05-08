package com.example.project.helper.animal;

import com.example.project.logic.logger.MyLogger;

public class Animal {

    private boolean vegetarian;

    private String eats;

    private int noOfLegs;

    public Animal(boolean veg, String food, int legs) {
        MyLogger.logMethodEntry("init", this, new Object[] { veg, food, legs },
                new Class<?>[] { boolean.class, String.class, int.class });
        this.vegetarian = veg;
        this.eats = food;
        this.noOfLegs = legs;
        MyLogger.logMethodExit("init", this, void.class, null, new Object[] { veg, food, legs },
                new Class<?>[] { boolean.class, String.class, int.class });
    }

    public boolean isVegetarian() {
        MyLogger.logMethodEntry("isVegetarian", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("isVegetarian", this, boolean.class, vegetarian, (Object[]) null, new Class<?>[0]);
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        MyLogger.logMethodEntry("setVegetarian", this, (new Object[] { vegetarian }), new Class<?>[] { boolean.class });
        this.vegetarian = vegetarian;
        MyLogger.logMethodExit("setVegetarian", this, void.class, null, (new Object[] { vegetarian }),
                new Class<?>[] { boolean.class });
    }

    public String getEats() {
        MyLogger.logMethodEntry("getEats", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("getEats", this, String.class, eats, (Object[]) null, new Class<?>[0]);
        return eats;
    }

    public void setEats(String eats) {
        MyLogger.logMethodEntry("setEats", this, (new Object[] { eats }), new Class<?>[] { String.class });
        this.eats = eats;
        MyLogger.logMethodExit("setEats", this, void.class, null, (new Object[] { eats }),
                new Class<?>[] { String.class });
    }

    public int getNoOfLegs() {
        MyLogger.logMethodEntry("getNoOfLegs", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("getNoOfLegs", this, int.class, noOfLegs, (Object[]) null, new Class<?>[0]);
        return noOfLegs;
    }

    public void setNoOfLegs(int noOfLegs) {
        MyLogger.logMethodEntry("setNoOfLegs", this, (new Object[] { noOfLegs }), new Class<?>[] { int.class });
        this.noOfLegs = noOfLegs;
        MyLogger.logMethodExit("setNoOfLegs", this,void.class, null, (new Object[] { noOfLegs }), new Class<?>[] { int.class });
    }

}
