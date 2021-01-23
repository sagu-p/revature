package com.vivek.paradiseapp;

class SalonRate {


    String emp_id;
    String emp_name;
    String emp_image;
    int emp_rate;
    String emp_email;
    String emp_mobile_no;
    String emp_pass;
    String emp_bdate;
    String emp_gen;
    String emp_add;
    String emp_qualification;
    String emp_sal;


    public SalonRate(String employeeid, String employeename, String employeeimage, int employeerate) {

        super();
        this.emp_id = employeeid;
        this.emp_name = employeename;
        this.emp_image = employeeimage;
        this.emp_rate = employeerate;


    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_image() {
        return emp_image;
    }

    public void setEmp_image(String emp_image) {
        this.emp_image = emp_image;
    }

    public int getEmp_rate() {
        return emp_rate;
    }

    public void setEmp_rate(int emp_rate) {
        this.emp_rate = emp_rate;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_mobile_no() {
        return emp_mobile_no;
    }

    public void setEmp_mobile_no(String emp_mobile_no) {
        this.emp_mobile_no = emp_mobile_no;
    }

    public String getEmp_pass() {
        return emp_pass;
    }

    public void setEmp_pass(String emp_pass) {
        this.emp_pass = emp_pass;
    }

    public String getEmp_bdate() {
        return emp_bdate;
    }

    public void setEmp_bdate(String emp_bdate) {
        this.emp_bdate = emp_bdate;
    }

    public String getEmp_gen() {
        return emp_gen;
    }

    public void setEmp_gen(String emp_gen) {
        this.emp_gen = emp_gen;
    }

    public String getEmp_add() {
        return emp_add;
    }

    public void setEmp_add(String emp_add) {
        this.emp_add = emp_add;
    }

    public String getEmp_qualification() {
        return emp_qualification;
    }

    public void setEmp_qualification(String emp_qualification) {
        this.emp_qualification = emp_qualification;
    }

    public String getEmp_sal() {
        return emp_sal;
    }

    public void setEmp_sal(String emp_sal) {
        this.emp_sal = emp_sal;
    }
}
