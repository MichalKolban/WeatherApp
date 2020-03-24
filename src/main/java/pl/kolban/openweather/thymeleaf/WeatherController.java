package pl.kolban.openweather.thymeleaf;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kolban.openweather.model.Weather;
import pl.kolban.openweather.model.WeatherModel;
import pl.kolban.openweather.utils.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;


@Controller
public class WeatherController {

    private final String API = "http://api.openweathermap.org/data/2.5/weather?";
    private final String UNITS = "&units=metric";                                    // possible farenheit later
    private final String APPID = "&APPID=38dd6ca5a75bd3ea14e326a1cba72197";
    private final String LANG = "&lang=pl";

    private final String ICON_API = "http://openweathermap.org/img/w/";
    private final String ICON_END = ".png";


    Utils utils;

    @Autowired
    public WeatherController(Utils utils) {
        this.utils = utils;
    }


    @PostMapping("/index")
    public String returnHome(){
        return "index";
    }

    @RequestMapping(value = "/city", method = RequestMethod.POST)
    public String getCityName(Model model, String city) throws IOException {
        WeatherModel weatherModel = new WeatherModel();

//        boolean onlyLetters = utils.cityNameValidation(city);
//        if (onlyLetters && city != null) {
        String link = API + "q=" + city + UNITS + APPID + LANG;
        URL url = new URL(link);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        weatherModel = new Gson().fromJson(reader, WeatherModel.class);
        System.out.println(weatherModel.toString());
//        }
        weatherModel = addWeatherIcon(weatherModel);
        model.addAttribute("weatherModel", weatherModel);
        return "city";
    }



    // === private methods ===


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
