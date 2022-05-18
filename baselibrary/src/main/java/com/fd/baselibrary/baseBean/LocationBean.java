package com.fd.baselibrary.baseBean;

public class LocationBean {
    private  String type;
    private  String userId;
    private  String longitude;
    private  String latitude;

    public LocationBean() {
    }

    public LocationBean(String type, String userId, String longitude, String latitude) {
        this.type = type;
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
