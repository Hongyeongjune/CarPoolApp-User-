package com.example.user.cardoners;

public class RequestitemActivity {

    private String distanceStr ;
    private String timeStr ;
    private String ampmStr ;
    private String start_addressStr ;
    private String end_addressStr ;
    private String id; //phone number

    public void setDistance(String distance) {
        distanceStr = distance ;
    }
    public void setTime(String time) {
        timeStr = time ;
    }
    public void setAmpm(String ampm) {
        ampmStr = ampm ;
    }
    public void setStart_address(String start_address) {
        start_addressStr = start_address ;
    }
    public void setEnd_address(String end_address) {
        end_addressStr= end_address ;
    }


    public String getDistance() {
        return this.distanceStr ;
    }
    public String getTime() {
        return this.timeStr ;
    }
    public String getAmpm() {
        return this.ampmStr ;
    }
    public String getStart_address() {
        return this.start_addressStr;
    }
    public String getEnd_address() {
        return this.end_addressStr;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}