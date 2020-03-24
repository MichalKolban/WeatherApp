package pl.kolban.openweather.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Utils {


    public Utils() {
    }


    public boolean cityNameValidation(String str) {
        if (str != null && str.matches("^[a-zA-Z]*$")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean zipCodeValidation(String str){
        if(str != null && str.matches("^[0-9\\-]*$")){
            return true;
        } else {
            return false;
        }
    }

    public boolean countryCodeValidation(String str){
        if(str.length() == 2 && cityNameValidation(str)){
            return true;
        } else {
            return false;
        }
    }

    public boolean longitudeAndLatitudeValidation(String str){
        if(str != null && str.matches("^[0-9\\,]*$")){
            return true;
        } else{
            return false;
        }
    }

    public String unixTimeToDateTimeFormat(String str){
        long unixTime = Long.valueOf(str);
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String formatedDate = sdf.format(date);
        return formatedDate;
    }

    public String unitTimeToTimeFormat(String str){
        long unixTime = Long.valueOf(str);
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formatedTime = sdf.format(date);
        return formatedTime;
    }


}
