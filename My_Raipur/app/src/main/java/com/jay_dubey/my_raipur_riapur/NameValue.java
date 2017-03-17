package com.jay_dubey.my_raipur_riapur;

/**
 * Created by Jay_Dubey on 3/4/2017.
 */

public class NameValue {
    int position, image;
    String name;

    public NameValue(int position, int image, String name) {
        this.position = position;
        this.image = image;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
