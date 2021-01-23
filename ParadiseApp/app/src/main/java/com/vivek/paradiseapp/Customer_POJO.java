package com.vivek.paradiseapp;

public class Customer_POJO {

    String name,email,num,gen,bdate,add,rp;
    String message;
    String status;

    public Customer_POJO(String show_status, String show_message)
    {
        super();
        this.status = show_status;
        this.message = show_message;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNum() {
        return num;
    }

    public String getGen() {
        return gen;
    }

    public String getBdate() {
        return bdate;
    }

    public String getAdd() {
        return add;
    }

    public String getRp() {
        return rp;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
