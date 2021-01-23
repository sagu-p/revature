package com.vivek.paradiseapp;

class Category_sub {

    String cid;
    String cname;
    String cimage;
    String cprice;
    String cdescription;

    public Category_sub(String subcatid, String subcatname, String subcatimage, String subcatprice, String subcatdescription) {
        super();
        this.cid=subcatid;
        this.cname=subcatname;
        this.cprice=subcatprice;
        this.cdescription=subcatdescription;
        this.cimage=subcatimage;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public String getCdescription() {
        return cdescription;
    }

    public void setCdescription(String cdescription) {
        this.cdescription = cdescription;
    }
}
