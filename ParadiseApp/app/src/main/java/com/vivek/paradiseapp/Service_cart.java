package com.vivek.paradiseapp;

class Service_cart {

    String cid;
    String csubcatid;
    String cname;
    String cimage;
    String cprice;

    public Service_cart(String serviceCartid, String subcatid, String subcatname, String subcatprice, String subcatimage) {
        super();
        this.cid=serviceCartid;
        this.csubcatid=subcatid;
        this.cname=subcatname;
        this.cprice=subcatprice;
        this.cimage=subcatimage;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCsubcatid() {
        return csubcatid;
    }

    public void setCsubcatid(String csubcatid) {
        this.csubcatid = csubcatid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCimage() {
        return cimage;
    }

    public void setCimage(String cimage) {
        this.cimage = cimage;
    }

    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice;
    }



}
