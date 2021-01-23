package com.vivek.paradiseapp;

class Appointment_POJO {

    public String app_id, app_date, app_time;

    public String show_status, show_message;

    public Appointment_POJO(String app_id, String app_date, String app_time) {
        super();
        this.app_id=app_id;
        this.app_date=app_date;
        this.app_time=app_time;
    }

    public Appointment_POJO(String show_status, String show_message) {

        super();
        this.show_status=show_status;
        this.show_message=show_message;

    }

    public void setShow_status(String show_status) {
        this.show_status = show_status;
    }

    public void setShow_message(String show_message) {
        this.show_message = show_message;
    }

    public String getShow_status() {
        return show_status;
    }

    public String getShow_message() {
        return show_message;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public void setApp_date(String app_date) {
        this.app_date = app_date;
    }

    public void setApp_time(String app_time) {
        this.app_time = app_time;
    }

    public String getApp_id() {
        return app_id;
    }

    public String getApp_date() {
        return app_date;
    }

    public String getApp_time() {
        return app_time;
    }
}
