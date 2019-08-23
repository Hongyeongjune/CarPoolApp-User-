package com.example.user.cardoners.dbmanager;

public class DriverHistory {

    private int _id;
    private String distance;
    private String time;
    private String departure;
    private String destination;

    public int get_id() {
        return _id;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }



}
