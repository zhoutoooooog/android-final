package com.example.tong.bean;

/**
 * Created by tong on 2017/12/27.
 */

public class building {
    private String building5,building13,building14,building8,building9;

    public String getBuilding5() {
        return building5;
    }
    public String getBuilding13() {
        return building13;
    }
    public String getBuilding14() {
        return building14;
    }
    public String getBuilding8() {
        return building8;
    }
    public String getBuilding9() {
        return building9;
    }

    public void setBuilding5(String building5) {
        this.building5 = building5;
    }
    public void setBuilding13(String building13) {
        this.building13 = building13;
    }
    public void setBuilding14(String building14) {
        this.building14 = building14;
    }
    public void setBuilding8(String building8) {
        this.building8 = building8;
    }
    public void setBuilding9(String building9) {
        this.building9 = building9;
    }

    @Override
    public String toString() {
        return "Building{" +
                "building5='" + building5 + '\'' +
                ", building13='" + building13 + '\'' +
                ", building14='" + building14 + '\'' +
                ", building8='" + building8 + '\'' +
                ", building9='" + building9 + '\'' +
                '}';
    }
}
