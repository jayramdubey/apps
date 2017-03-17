package com.jay_dubey.my_raipur_riapur;

import java.io.Serializable;

/**
 * Created by Jay_Dubey on 3/6/2017.
 */

public class FacilityList implements Serializable {
    int position, image;
    double longitude, latitude;
    String name, address;

    public FacilityList(int position, int image, double longitude, double latitude, String name, String address) {
        this.position = position;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
        this.address = address;


    }

    public int getPosition() {
        return position;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getImage() {
        return image;
    }
}
