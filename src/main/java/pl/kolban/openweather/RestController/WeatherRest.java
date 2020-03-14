package pl.kolban.openweather.RestController;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kolban.openweather.model.WeatherModel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
public class WeatherRest {

    private final String API = "http://api.openweathermap.org/data/2.5/weather?";
    private final String UNITS = "&units=metric";                                    // possible farenheit later
    private final String APPID = "&APPID=38dd6ca5a75bd3ea14e326a1cba72197";


//    NA PODSTAWIE NAZWY MIASTA

    @GetMapping("/city/{cityname}")
    public WeatherModel getJson(@PathVariable String cityname) throws IOException {

        String link = API + "q=" + cityname + UNITS + APPID;

        URL url = new URL(link);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        WeatherModel model = new Gson().fromJson(reader, WeatherModel.class);
        return model;
    }

    // NA PODSTAWIE WSPÓłRZĘDNYCh

    @GetMapping("/coordinate/{lat}/{lon}")
    public WeatherModel getByCoordinates(@PathVariable String lat, @PathVariable String lon) throws IOException {

        // validate String (only numbers and commas)

        String latitude = "lat=" + lat;
        String longitude = "&lon=" + lon;

        String link = API + latitude + longitude + APPID;

        URL url = new URL(link);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        WeatherModel model = new Gson().fromJson(reader, WeatherModel.class);
        return model;
    }

    // NA podstawie zipcode (Only pl for now)

    @GetMapping("/zipcode/{zipNumber}/{countryCode}")
    public WeatherModel getByZipCode(@PathVariable String zipNumber, @PathVariable String countryCode) throws IOException {

        // validate zipNumber
        // validate countryCode

        String link = API + "zip=" + zipNumber + "," + countryCode + APPID;

        URL url = new URL(link);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        WeatherModel model = new Gson().fromJson(reader, WeatherModel.class);
        return model;
    }

}
