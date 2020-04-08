package pl.kolban.openweather.model;

import com.google.gson.annotations.SerializedName;
import pl.kolban.openweather.utils.Utils;

import java.util.List;

public class WeatherModel {

    @SerializedName("coord")
    private Coordinate coordinate;
    @SerializedName("weather")
    private List<Weather> weather = null;
    @SerializedName("main")
    private Temperature temperature;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("dt")
    private String lastUpdateTimeStamp;
    @SerializedName("sys")
    private SunSystem sunSystem;
    @SerializedName("timezone")
    private Integer timezone;
    @SerializedName("id")
    private Integer cityId;
    @SerializedName("name")
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

    public SunSystem getSunSystem() {
        return sunSystem;
    }

    public void setSunSystem(SunSystem sunSystem) {
        this.sunSystem = sunSystem;
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
                "coordinate=" + coordinate +
                ", weather=" + weather +
                ", temperature=" + temperature +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", lastUpdateTimeStamp='" + lastUpdateTimeStamp + '\'' +
                ", sunSystem=" + sunSystem +
                ", timezone=" + timezone +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
