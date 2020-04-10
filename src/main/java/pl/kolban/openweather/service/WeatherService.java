package pl.kolban.openweather.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kolban.openweather.model.SunSystem;
import pl.kolban.openweather.model.Weather;
import pl.kolban.openweather.model.WeatherModel;
import pl.kolban.openweather.utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
public class WeatherService {

    private final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private final String API = "http://api.openweathermap.org/data/2.5/weather?";
    private final String UNITS = "&units=metric";                                    // possible farenheit later
    private final String APPID = "&APPID=38dd6ca5a75bd3ea14e326a1cba72197";
    private final String LANG = "&lang=pl";

    private final String ZIP_CODE = "zip=";
    private final String COUNTRY_CODE = ",pl";
    private final String CITY_ID = "id=";

    private final String ICON_API = "http://openweathermap.org/img/w/";
    private final String ICON_END = ".png";

    WeatherModel weatherModel;

    Utils utils;

    @Autowired
    public WeatherService(Utils utils) {
        this.utils = utils;
    }

    public WeatherModel getByCityName(String city) throws IOException {
        try {
//        String link = API + "q=" + city + UNITS + APPID + LANG;   // with pl language
            String link = API + "q=" + city + UNITS + APPID;
            weatherModel = getWeatherModelFromWeb(link);
            return weatherModel;
        } catch (FileNotFoundException ex) {
            log.error("WeatcherService.getByCityName() : File not found : String [ " + city + " ]");
        }
        return null;
    }


    public WeatherModel getByCoordinate(String lat, String lon) throws IOException {
        // valid data
        String latitude = "lat=" + lat;
        String longitude = "&lon=" + lon;
//        String link = API + latitude + longitude + UNITS + APPID + LANG;
        String link = API + latitude + longitude + UNITS + APPID;
        weatherModel = getWeatherModelFromWeb(link);
        return weatherModel;
    }

    public WeatherModel getByZipCode(String zipCode) throws IOException {
//        String link = API + ZIP_CODE + zipCode + COUNTRY_CODE + UNITS + APPID + LANG;
        String link = API + ZIP_CODE + zipCode + COUNTRY_CODE + UNITS + APPID;
        weatherModel = getWeatherModelFromWeb(link);
        return weatherModel;
    }

    public WeatherModel getByCityId(int cityIdNumber) throws IOException {
        String link = API + CITY_ID + cityIdNumber +  UNITS + APPID;
        weatherModel = getWeatherModelFromWeb(link);
        return weatherModel;
    }

    // private

    private WeatherModel getDataFromOpenWeatherApi(String link) throws IOException {
        URL url = new URL(link);
        InputStreamReader reader = null;

        try {
            reader = new InputStreamReader(url.openStream());
        } catch (FileNotFoundException e) {
            log.error("WeatherService.getDataFromOpenWeatherApi : link " + link);
            return null;
        }
        weatherModel = new Gson().fromJson(reader, WeatherModel.class);
        return weatherModel;
    }

    private WeatherModel addWeatherIcon(WeatherModel weatherModel) {

        if (weatherModel == null) {
            return null;
        }
        String iconString = "";
        List<Weather> weatherList = weatherModel.getWeather();
        for (Weather w : weatherList) {
            iconString = w.getIcon();
        }
        String imgLink = ICON_API + iconString + ICON_END;
        for (Weather w : weatherList) {
            w.setIcon(imgLink);
        }
        weatherModel.setWeather(weatherList);
        return weatherModel;
    }

    private WeatherModel calculateSunriseAndSunset(WeatherModel weatherModel) {

        if (weatherModel == null) {
            return null;
        }

        String sunriseFromEndpoint = weatherModel.getSunSystem().getSunrise();
        String sunsetFromEndpoint = weatherModel.getSunSystem().getSunset();
        String formatDateForSunrise = utils.unitTimeToTimeFormat(sunriseFromEndpoint);
        String formatDateForSunset = utils.unitTimeToTimeFormat(sunsetFromEndpoint);
        String sunriseProperTime = utils.calculateProperTime(formatDateForSunrise, weatherModel.getTimezone());
        String sunsetProperTime = utils.calculateProperTime(formatDateForSunset, weatherModel.getTimezone());

        SunSystem sun = weatherModel.getSunSystem();
        sun.setSunrise(sunriseProperTime);
        sun.setSunset(sunsetProperTime);

        return weatherModel;
    }


    private WeatherModel getWeatherModelFromWeb(String link) throws IOException {
        try {
            weatherModel = getDataFromOpenWeatherApi(link);
            weatherModel = addWeatherIcon(weatherModel);
            weatherModel = calculateSunriseAndSunset(weatherModel);
            return weatherModel;
        } catch (FileNotFoundException e) {
            log.error("WeatherService.getWeatherModelFromWeb : link " + link);
            return null;
        }
    }


}
