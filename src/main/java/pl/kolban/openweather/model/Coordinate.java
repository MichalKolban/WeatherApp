package pl.kolban.openweather.model;

import com.google.gson.annotations.SerializedName;

public class Coordinate {

    @SerializedName("lon")
    private Double lon;
    @SerializedName("lat")
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "CoordinateModel{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
