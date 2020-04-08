package pl.kolban.openweather.model;

import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("temp")
    private String temp;
    @SerializedName("temp_min")
    private String tempMin;
    @SerializedName("temp_max")
    private String tempMax;
    @SerializedName("pressure")
    private String pressure;
    @SerializedName("humidity")
    private String humidity;

    public String getTemp() {
        if (!temp.contains("C")) {
            temp = temp + " C";
        } else {
            return temp;
        }
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTempMin() {
        if (!tempMin.contains("C")) {
            tempMin = tempMin + " C";
        } else {
            return tempMin;
        }
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        if (!tempMax.contains("C")) {
            tempMax = tempMax + " C";
        } else {
            return tempMax;
        }
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getPressure() {
        if (!pressure.contains("Pa")) {
            pressure = pressure + " Pa";
        } else {
            return pressure;
        }
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        if (!humidity.contains("%")) {
            humidity = humidity + " %";
        } else {
            return humidity;
        }
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "TemperatureModel{" +
                "temp=" + temp +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
