package com.vivek.paradiseapp;

class AppointmentService {

    String Aid,Asubcatid,Asubcatname,Asubcatprice,Aimage;

    public AppointmentService(String appointmentid, String subcatid, String subcatname, String subcatprice, String subcatimage) {

        super();
        this.Aid=appointmentid;
        this.Asubcatid=subcatid;
        this.Asubcatname=subcatname;
        this.Asubcatprice=subcatprice;
        this.Aimage=subcatimage;


    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getAsubcatid() {
        return Asubcatid;
    }

    public void setAsubcatid(String asubcatid) {
        Asubcatid = asubcatid;
    }

    public String getAsubcatname() {
        return Asubcatname;
    }

    public void setAsubcatname(String asubcatname) {
        Asubcatname = asubcatname;
    }

    public String getAsubcatprice() {
        return Asubcatprice;
    }

    public void setAsubcatprice(String asubcatprice) {
        Asubcatprice = asubcatprice;
    }

    public String getAimage() {
        return Aimage;
    }

    public void setAimage(String aimage) {
        Aimage = aimage;
    }
}
