package pl.kolban.openweather.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

//    @RequestMapping(value = "/city", method = RequestMethod.POST)
//    public String getCityName(Model model, String city) throws IOException {
//        WeatherModel weatherModel = service.getByCityName(city);
//        if(weatherModel != null) {
//            model.addAttribute("weatherModel", weatherModel);
//            return "city";
//        } else {
//            log.error("WeatherController.getCityName(): Wrong parameter : " + city);
//            return "wrongpage";
//        }
//    }



//    @RequestMapping(value = "/coordinate", method = RequestMethod.POST)
//    public String getLatitudeAndLongitude(Model model, String lat, String lon) throws IOException {
//        WeatherModel coordinateModel = service.getByCoordinate(lat, lon);
//        if(coordinateModel != null) {
//            model.addAttribute("coordinateModel", coordinateModel);
//            return "coordinate";
//        } else {
//            log.error("WeatherController.getLatitudeAndLongitude(): Wrong parametes : " + lat + " " + lon);
//            return "wrongpage";
//        }
//    }


//    @RequestMapping(value = "/zipcode", method = RequestMethod.POST)
//    public String getZipCode(Model model, String zipcode) throws IOException {
//        WeatherModel zipCodeModel = service.getByZipCode(zipcode);
//        if(zipCodeModel != null) {
//            model.addAttribute("zipCodeModel", zipCodeModel);
//            return "zipcode";
//        } else {
//            log.error("WeatherController.getZipCode(): Wrong parametes : " + zipcode);
//            return "wrongpage";
//        }
//    }


    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public String getData(Model model, String dataWeb) throws IOException {

        if(dataWeb.isEmpty()) {
            return "wrongpage";
        }

        boolean dataCity = utils.cityNameValidation(dataWeb);
        boolean dataZipCode = utils.zipCodeValidation(dataWeb);
        boolean dataLatLon = utils.coordinateValidation(dataWeb);

        if (dataCity) {
            String city = dataWeb;
            WeatherModel weatherModel = service.getByCityName(city);
            if (weatherModel != null) {
                model.addAttribute("weatherModel", weatherModel);
                return "cityWeather";
            } else {
                log.error("WeatherController.getByCityName(): Wrong parameter : " + city);
                return "wrongpage";
            }

        } else if (dataZipCode) {
            String zipcode = dataWeb;
            WeatherModel zipCodeModel = service.getByZipCode(zipcode);
            if(zipCodeModel != null) {
                model.addAttribute("weatherModel", zipCodeModel);
                return "cityWeather";
            } else {
                log.error("WeatherController.getByZipCode(): Wrong parametes : " + zipcode);
                return "wrongpage";
            }

        } else if (dataLatLon) {

            String[] parts = utils.devideGeoPoints(dataWeb);
            String lon = parts[0];
            String lat = parts[1];
            WeatherModel coordinateModel = service.getByCoordinate(lat, lon);
            if(coordinateModel != null) {
                model.addAttribute("weatherModel", coordinateModel);
                return "cityWeather";
            } else {
                log.error("WeatherController.getByCoordinate(): Wrong parametes : " + dataLatLon);
                return "wrongpage";
            }
        } else {
            log.error("WeatherController.getData(): Wrong parameters " + dataWeb);
        }
        return "wrongpage";
    }

    @GetMapping("/moreInfo/{city}")
    public String getMoreInfo(Model model, @PathVariable String city) throws IOException {
        WeatherModel weatherModel = service.getByCityName(city);
        if (weatherModel != null) {
            model.addAttribute("weatherModel", weatherModel);
            return "moreInfoCityWeather";
        } else {
            return "wrongPage";
        }
    }
}
