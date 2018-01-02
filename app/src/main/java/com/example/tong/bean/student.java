package com.example.tong.bean;

/**
 * Created by tong on 2017/12/27.
 */

public class student {
    private String studentid;
    private String name;
    private String gender;
    private String vcode;
    private String room;
    private String building;
    private String location;
    private String grade;


    public String getStudentid() {
        return studentid;
    }
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public String getVcode() {
        return vcode;
    }
    public String getRoom() {
        return room;
    }
    public String getBuilding() {
        return building;
    }
    public String getLocation() {
        return location;
    }
    public String getGrade() {
        return grade;
    }


    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentid='" + studentid + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", vcode='" + vcode + '\'' +
                ", room='" + room + '\'' +
                ", building='" + building + '\'' +
                ", location='" + location + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
