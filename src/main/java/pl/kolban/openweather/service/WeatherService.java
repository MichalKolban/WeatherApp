package pl.kolban.openweather.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kolban.openweather.model.Weather;
import pl.kolban.openweather.model.WeatherModel;
import pl.kolban.openweather.utils.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
public class WeatherService {

    private final String API = "http://api.openweathermap.org/data/2.5/weather?";
    private final String UNITS = "&units=metric";                                    // possible farenheit later
    private final String APPID = "&APPID=38dd6ca5a75bd3ea14e326a1cba72197";
    private final String LANG = "&lang=pl";

    private final String ICON_API = "http://openweathermap.org/img/w/";
    private final String ICON_END = ".png";

    WeatherModel weatherModel;


    Utils utils;

    @Autowired
    public WeatherService(Utils utils) {
        this.utils = utils;
    }

    public WeatherModel getByCityName (String city) throws IOException {

        boolean cityValid = utils.cityNameValidation(city);

        if(cityValid) {
            // valid data

//        String link = API + "q=" + city + UNITS + APPID + LANG;   // with pl language
            String link = API + "q=" + city + UNITS + APPID;
            weatherModel = getDataFromOpenWeatherApi(link);
            System.out.println(weatherModel.toString());
            weatherModel = addWeatherIcon(weatherModel);
            return weatherModel;
        } else {
            return null;
        }
    }

    public WeatherModel getByCoordinate(String lat, String lon) throws IOException {

        // valid data

        String latitude = "lat=" + lat;
        String longitude = "&lon=" + lon;
        String link = API + latitude + longitude + APPID + LANG;
        weatherModel = getDataFromOpenWeatherApi(link);
        weatherModel = addWeatherIcon(weatherModel);
        return weatherModel;
    }





    // private

    private WeatherModel getDataFromOpenWeatherApi(String link) throws IOException {
        URL url = new URL(link);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        weatherModel = new Gson().fromJson(reader, WeatherModel.class);
        return weatherModel;
    }

    private WeatherModel addWeatherIcon(WeatherModel weatherModel) {
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


}
