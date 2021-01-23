package com.vivek.paradiseapp;

class Category_main {

    String cid;
    String cname;
    String cimage;

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


    public Category_main(String cid, String cname, String cimage) {
        super();
        this.cid=cid;
        this.cname=cname;
        this.cimage=cimage;

    }
}
