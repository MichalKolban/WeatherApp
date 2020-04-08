package pl.kolban.openweather.model;

import com.google.gson.annotations.SerializedName;
import pl.kolban.openweather.utils.Utils;

public class Wind {

    @SerializedName("speed")
    private Double speed;
    @SerializedName("deg")
    private String deg;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getDeg() {
        Utils utils = new Utils();
        String windDegDescription = utils.calculateWindDirection(deg);
        return windDegDescription;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
