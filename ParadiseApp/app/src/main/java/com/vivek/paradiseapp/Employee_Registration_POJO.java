package com.vivek.paradiseapp;

public class Employee_Registration_POJO {

        String email;
        String pass;
        String message;
        String status;

        public Employee_Registration_POJO(String show_status, String show_message)
        {
            super();
            this.status = show_status;
            this.message = show_message;
        }

        public String getEmail() {  return email;}

        public String getPass() {        return pass;    }

        public String getMessage() {        return message;    }

        public String getStatus() {        return status;    }

        public void setEmail(String email) {        this.email = email;    }

        public void setPass(String pass) {        this.pass = pass;    }

        public void setMessage(String message) {        this.message = message;    }

        public void setStatus(String status) {        this.status = status;    }

}



