package com.vivek.paradiseapp;

class Ragistration_POJO {

    String email;
    String pass;
    String message;
    String status;

    public Ragistration_POJO(String show_status, String show_message) {
        super();
        this.status = show_status;
        this.message = show_message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
