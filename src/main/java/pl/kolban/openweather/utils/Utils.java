package pl.kolban.openweather.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Utils {


    public Utils() {
    }

    public boolean cityNameValidation(String str) {
        if (str != null && str.matches("^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean zipCodeValidation(String str){
        if(str != null && str.matches("^[0-9]{2}-[0-9]{3}$")){
            return true;
        } else {
            return false;
        }
    }

    public boolean coordinateValidation(String str){
        if(str != null && str.matches("^([-+]?\\d{1,2}([.]\\d+)?),\\s*([-+]?\\d{1,3}([.]\\d+)?)$")){
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

    public String[] devideGeoPoints(String str){
        String[] all = str.split(",");
        return all;
    }

    public String calculateWindDirection(String str){
        int direction = Integer.valueOf(str);

        if(direction >= 11 && direction <= 79){
            return "NORTH-EAST (" + direction + ")";
        } else if (direction >= 80 && direction <= 100) {
            return "EAST  (" + direction + ")";
        } else if (direction >= 101 && direction <= 169) {
            return "SOUTH-EAST (" + direction + ")";
        } else if (direction >= 170 && direction <= 190) {
            return "SOUTH (" + direction + ")";
        } else if (direction >= 191 && direction <= 259) {
            return "SOUTH-WEST (" + direction + ")";
        } else if (direction >= 260 && direction <= 280) {
            return "WEST (" + direction + ")";
        } else if (direction >= 281 && direction <= 349) {
            return "NORTH-WEST (" + direction + ")";
        } else  {
            return "NORTH";
        }
    }

}
