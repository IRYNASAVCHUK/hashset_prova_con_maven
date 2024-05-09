package com.example.project.helper.animal;

import com.example.project.logic.logger.MyLogger;

public class Cat extends Animal {

    private String color;

    public Cat(boolean veg, String food, int legs) {
        super(veg, food, legs);
        MyLogger.logMethodEntry("init", this, new Object[] { veg, food, legs },
                new Class<?>[] { boolean.class, String.class, int.class });
        this.color = "White";
        MyLogger.logMethodExit("init", this, void.class, null, new Object[] { veg, food, legs },
                new Class<?>[] { boolean.class, String.class, int.class });
    }

    public Cat(boolean veg, String food, int legs, String color) {
        super(veg, food, legs);
        MyLogger.logMethodEntry("init", this, new Object[] { veg, food, legs, color },
                new Class<?>[] { boolean.class, String.class, int.class, String.class });
        this.color = color;
        MyLogger.logMethodExit("init", this, void.class, null, new Object[] { veg, food, legs, color },
                new Class<?>[] { boolean.class, String.class, int.class, String.class });
    }

    public String getColor() {
        MyLogger.logMethodEntry("getColor", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("getColor", this, String.class, color, (Object[]) null, new Class<?>[0]);
        return color;
    }

    public void setColor(String color) {
        MyLogger.logMethodEntry("setColor", this, (new Object[] { color }), new Class<?>[] { String.class });
        this.color = color;
        MyLogger.logMethodExit("setColor", this, void.class, null, (new Object[] {color }),
        new Class<?>[] { String.class });
    }

}