package pl.kolban.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pl.kolban.openweather.utils.Utils;

import java.util.List;

public class WeatherModel {

    @SerializedName("coord")
    @Expose
    private Coordinate coordinate;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("main")
    @Expose
    private Temperature temperature;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private String lastUpdateTimeStamp;
    @SerializedName("sys")
    @Expose
    private System sys;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("id")
    @Expose
    private Integer cityId;
    @SerializedName("name")
    @Expose
    private String cityName;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getLastUpdateTimeStamp() {
        Utils utils = new Utils();
        String formatedTime = utils.unixTimeToDateTimeFormat(lastUpdateTimeStamp);
        return formatedTime;
    }

    public void setLastUpdateTimeStamp(String lastUpdateTimeStamp) {
        this.lastUpdateTimeStamp = lastUpdateTimeStamp;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature main) {
        this.temperature = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public System getSys() {
        return sys;
    }

    public void setSys(System sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        int hours = timezone / 3600;
        return hours;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "coordinateModel=" + coordinate +
                ", weather=" + weather +
                ", temperatureModel=" + temperature +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", lastUpdateTimeStamp='" + lastUpdateTimeStamp + '\'' +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
