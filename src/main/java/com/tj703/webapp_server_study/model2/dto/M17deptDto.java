package com.tj703.webapp_server_study.model2.dto;

public class M17deptDto {

//    employees.departments.dept_no
//    employees.departments.dept_name
    private String deptNoStr;
    private String deptName;

    @Override
    public String toString() {
        return "{" +
                "dept_no='" + deptNoStr + '\'' +
                ", dept_name='" + deptName + '\'' +
                "}\n";
    }

    public String getDeptNoStr() {
        return deptNoStr;
    }
    public void setDeptNoStr(String deptNoStr) {
        this.deptNoStr = deptNoStr;
    }

    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}


