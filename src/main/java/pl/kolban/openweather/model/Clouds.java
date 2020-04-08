package pl.kolban.openweather.model;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    private String all;

    public String getAll() {
        if (!all.contains("%")) {
            all = all + " %";
        } else {
            return all;
        }
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "CloudsModel{" +
                "all=" + all +
                '}';
    }
}
