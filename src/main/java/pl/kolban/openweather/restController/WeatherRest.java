package pl.kolban.openweather.restController;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kolban.openweather.model.WeatherModel;
import pl.kolban.openweather.utils.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
public class WeatherRest {

    Utils utils;

    @Autowired
    public WeatherRest(Utils utils) {
        this.utils = utils;
    }


    private final String API = "http://api.openweathermap.org/data/2.5/weather?";
    private final String UNITS = "&units=metric";                                    // possible farenheit later
    private final String APPID = "&APPID=38dd6ca5a75bd3ea14e326a1cba72197";
    private final String LANG = "&lang=pl";

//    city name

    @GetMapping("/city/{cityname}")
    public WeatherModel getByCityName(@PathVariable String cityname) throws IOException {

        boolean onlyLetters = utils.cityNameValidation(cityname);

        if(onlyLetters){

            String link = API + "q=" + cityname + UNITS + APPID + LANG;
            URL url = new URL(link);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            WeatherModel model = new Gson().fromJson(reader, WeatherModel.class);
            return model;

        } else {
            //  return some error
            return new WeatherModel();
        }

    }

    // coordinates

    @GetMapping("/coordinate/{lat}/{lon}")
    public WeatherModel getByCoordinates(@PathVariable String lat, @PathVariable String lon) throws IOException {

        boolean validateLatitude = utils.longitudeAndLatitudeValidation(lat);
        boolean validateLongitude = utils.longitudeAndLatitudeValidation(lon);

        if (validateLatitude && validateLongitude) {

            String latitude = "lat=" + lat;
            String longitude = "&lon=" + lon;

            String link = API + latitude + longitude + APPID + LANG;

            URL url = new URL(link);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            WeatherModel model = new Gson().fromJson(reader, WeatherModel.class);
            return model;

        } else {
            //  return some error
            return null;
        }
    }

    // zipcode (Only pl for now)

    @GetMapping("/zipcode/{zipNumber}/{countryCode}")
    public WeatherModel getByZipCode(@PathVariable String zipNumber, @PathVariable String countryCode) throws IOException {

        boolean validateZipCode = utils.zipCodeValidation(zipNumber);
        boolean validateCountryCode = utils.countryCodeValidation(countryCode);

        if (validateZipCode && validateCountryCode) {

            String link = API + "zip=" + zipNumber + "," + countryCode + APPID + LANG;

            URL url = new URL(link);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            WeatherModel model = new Gson().fromJson(reader, WeatherModel.class);
            return model;
        } else {
            //  return some error
            return null;
        }
    }

}
