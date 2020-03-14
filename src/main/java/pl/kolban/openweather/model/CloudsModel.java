package pl.kolban.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloudsModel {

    @SerializedName("all")
    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "CloudsModel{" +
                "all=" + all +
                '}';
    }
}
