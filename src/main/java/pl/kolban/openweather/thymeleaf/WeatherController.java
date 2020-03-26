package pl.kolban.openweather.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kolban.openweather.model.WeatherModel;
import pl.kolban.openweather.service.WeatherService;
import pl.kolban.openweather.utils.Utils;

import java.io.IOException;


@Controller
public class WeatherController {

    private final Logger log = LoggerFactory.getLogger(WeatherController.class);

    Utils utils;
    WeatherService service;


    @Autowired
    public WeatherController(Utils utils, WeatherService service) {
        this.utils = utils;
        this.service = service;
    }


    @PostMapping("/index")
    public String returnHome(){
        return "index";
    }

    @RequestMapping(value = "/city", method = RequestMethod.POST)
    public String getCityName(Model model, String city) throws IOException {
        WeatherModel weatherModel = service.getByCityName(city);

        if(weatherModel != null) {

            model.addAttribute("weatherModel", weatherModel);
            return "city";
        } else {
            log.error("WeatherController.getCityName(): Wrong parameter : " + city);
            return "wrongpage";
        }
    }

    @RequestMapping(value = "/coordinate", method = RequestMethod.POST)
    public String getLatitudeAndLongitude(Model model, String lat, String lon) throws IOException {
        WeatherModel coordinateModel = service.getByCoordinate(lat, lon);

        if(coordinateModel != null) {

            model.addAttribute("coordinateModel", coordinateModel);
            return "coordinate";
        } else {
            log.error("WeatherController.getLatitudeAndLongitude(): Wrong parametes : " + lat + " " + lon);
            return "wrongpage";
        }
    }






}
