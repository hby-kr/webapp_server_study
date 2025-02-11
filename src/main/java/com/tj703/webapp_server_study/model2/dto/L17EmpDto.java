package com.tj703.webapp_server_study.model2.dto;

public class L17EmpDto {
    // DB를 전송하는 객체를 만들자. 파싱하기 위한 객체. Bean구조.
    // Map 자료로 구조로 받아도 되지만, 따로 만드는 것.

//    employees.employees.emp_no
//    employees.employees.birth_date
//    employees.employees.first_name
//    employees.employees.last_name
//    employees.employees.gender
//    employees.employees.hire_date
    private int empno;
    private String firstname;
    private String lastname;
    private String birthday;
    private String gender;
    private String hiredate;

    @Override
    public String toString() {
        return "{" +
                "empno=" + empno +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", hiredate='" + hiredate + '\'' +
                "}\n";
    }

    public int getEmpno() {
        return empno;
    }
    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHiredate() {
        return hiredate;
    }
    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

}


