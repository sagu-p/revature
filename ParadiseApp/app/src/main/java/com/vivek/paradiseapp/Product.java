package com.vivek.paradiseapp;

class Product {

    String Pid;
    String Pname;
    String Pcat;
    String Pimage;
    String Pprice;
    String Pdescription;
    String Pquantity;

    public Product(String productid, String productname, String productcat, String productimage, String productprice, String productdescription, String productquantity) {
        super();
        this.Pid=productid;
        this.Pname=productname;
        this.Pcat=productcat;
        this.Pimage=productimage;
        this.Pprice=productprice;
        this.Pdescription=productdescription;
        this.Pquantity=productquantity;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPcat() {
        return Pcat;
    }

    public void setPcat(String pcat) {
        Pcat = pcat;
    }

    public String getPimage() {
        return Pimage;
    }

    public void setPimage(String pimage) {
        Pimage = pimage;
    }

    public String getPprice() {
        return Pprice;
    }

    public void setPprice(String pprice) {
        Pprice = pprice;
    }

    public String getPdescription() {
        return Pdescription;
    }

    public void setPdescription(String pdescription) {
        Pdescription = pdescription;
    }

    public String getPquantity() {
        return Pquantity;
    }

    public void setPquantity(String pquantity) {
        Pquantity = pquantity;
    }
}
