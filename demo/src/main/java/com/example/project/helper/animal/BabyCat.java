package com.example.project.helper.animal;

import com.example.project.logic.logger.MyLogger;

public class BabyCat extends Cat {
    private String sound;

    public BabyCat(boolean veg, String food, int legs, String color, String sound) {
        super(veg, food, legs, color);
        MyLogger.logMethodEntry("init", this, new Object[] { veg, food, legs,color },
                new Class<?>[] { boolean.class, String.class, int.class, String.class });
        this.sound = sound;
        MyLogger.logMethodExit("init", this, void.class, null, new Object[] { veg, food, legs, color },
        new Class<?>[] { boolean.class, String.class, int.class, String.class });
    }

    public String getSound() {
        MyLogger.logMethodEntry("getSound", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("getSound", this, String.class, sound, (Object[]) null, new Class<?>[0]);
        return sound;
    }

    public void setSound(String sound) {
        MyLogger.logMethodEntry("setColor", this, (new Object[] {sound }), new Class<?>[] { String.class });
        this.sound = sound;
        MyLogger.logMethodExit("setColor", this, void.class, null, (new Object[] {sound}),
        new Class<?>[] { String.class });
    }
}
