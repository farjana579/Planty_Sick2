package com.example.plantysick;

import java.util.ArrayList;

public class Plants {
    ArrayList< Plant > data = new ArrayList < Plant > ();
    public ArrayList<Plant> getData() {
        return data;
    }
    public void setData(ArrayList<Plant> data) {
        this.data = data;
    }
}
class Plant{
    private int id;
    private String common_name;
    ArrayList < Object > scientific_name = new ArrayList < Object > ();
    ArrayList < Object > other_name = new ArrayList < Object > ();
    private String cycle;
    private String watering;
    ArrayList < Object > sunlight = new ArrayList < Object > ();
    Default_image Default_imageObject;


    // Getter Methods

    public int getId() {
        return id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public String getCycle() {
        return cycle;
    }

    public String getWatering() {
        return watering;
    }

    public Default_image getDefault_image() {
        return Default_imageObject;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public void setDefault_image(Default_image default_imageObject) {
        this.Default_imageObject = default_imageObject;
    }
}
class Default_image {
    private float license;
    private String license_name;
    private String license_url;
    private String original_url;
    private String regular_url;
    private String medium_url;
    private String small_url;
    private String thumbnail;


    // Getter Methods

    public float getLicense() {
        return license;
    }

    public String getLicense_name() {
        return license_name;
    }

    public String getLicense_url() {
        return license_url;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public String getRegular_url() {
        return regular_url;
    }

    public String getMedium_url() {
        return medium_url;
    }

    public String getSmall_url() {
        return small_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    // Setter Methods

    public void setLicense(float license) {
        this.license = license;
    }

    public void setLicense_name(String license_name) {
        this.license_name = license_name;
    }

    public void setLicense_url(String license_url) {
        this.license_url = license_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }

    public void setRegular_url(String regular_url) {
        this.regular_url = regular_url;
    }

    public void setMedium_url(String medium_url) {
        this.medium_url = medium_url;
    }

    public void setSmall_url(String small_url) {
        this.small_url = small_url;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}